package com.mark.backend.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mark.backend.model.ErrorCodeModel;
import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserExample;
import com.mark.backend.utils.MarkUtils;

/**
 * 微信定时获得token
 * 
 * @Title:WeixinService
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月17日
 * 
 */
@Service
public class WeixinService {
	public static String access_token = null;

	public static String auth_access_token = null;

	public static String refresh_auth_access_token = null;

	public static Map<String, Map<String, Object>> markInfoMap = new HashMap<String, Map<String, Object>>();
	/**
	 * Id---user 对象map
	 */
	public static Map<Long, User> userMap = new HashMap<Long, User>();

	@Resource
	private UserMapper userMapper;

	ScheduledExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

	ExecutorService multiExecutor = Executors.newFixedThreadPool(5);

	@PostConstruct
	private void init() {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				markLoad();
				System.out.println("定时获取Token开始");
			}
		});
		executor.scheduleAtFixedRate(new getTokenSchedule(), 1, 7000,
				TimeUnit.SECONDS);
	}

	public void markLoad() {
		Map<String, Object> userIdMap = getUserIdMap();
		markInfoMap.put("userIdMap", userIdMap);
	}

	/**
	 * 获得用户openid和id 对应map
	 * 
	 * @return
	 */
	private Map<String, Object> getUserIdMap() {
		UserExample ex = new UserExample();
		ex.createCriteria();
		List<User> userList = userMapper.selectByExample(ex);
		Map<String, Object> map = new HashMap<String, Object>();
		for (User user : userList) {
			userMap.put(user.getId(), user);
			map.put(user.getOpenid(), user.getId());
		}
		return map;
	}

	class getTokenSchedule implements Runnable {
		@Override
		public void run() {
			access_token = MarkUtils.getAccessToken().getAccess_token();
			while (access_token == null) {
				access_token = MarkUtils.getAccessToken().getAccess_token();
			}
		}
	}

	public String createMenu(String jsonStr) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ access_token;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonStr, "UTF-8"));
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "失败！";
			}
			if (entity != null) {
				ErrorCodeModel ecm = new ErrorCodeModel();
				// 响应内容
				String content = EntityUtils.toString(entity);
				JSONObject.parseObject(content, ErrorCodeModel.class);
				if (ecm.getErrcode() != "0") {
					return "失败！";
				} else {
					return "成功";
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "失败！";
	}

	public Object getUserInfo(String code, String status) {
		/**
		 * 根据获得的code去得到用户的openid
		 */
		final String openId = MarkUtils.getAuthJsonobject(code);
		if (openId == null) {
			return null;
		}
		/**
		 * 查看用户是否已存在mark数据库中，若无插入并从微信拉取用户部分信息数据 阻塞执行。若存在，更新用户的昵称和头像信息，异步执行
		 */
		if (!markInfoMap.get("userIdMap").containsKey(openId)) {
			JSONObject userInfo = MarkUtils.getUserInfo(access_token, openId);
			User user = new User();
			user.setCreateTime(MarkUtils.getCurrentTime());
			user.setUpdateTime(user.getCreateTime());
			user.setCity(userInfo.getString("city"));
			user.setProvince(userInfo.getString("province"));
			user.setNickname(userInfo.getString("nickname"));
			user.setGender(userInfo.getString("sex"));
			user.setHeadImgUrl(userInfo.getString("headimgurl"));
			user.setOpenid(userInfo.getString("openid"));
			int i = userMapper.insert(user);
			if (i > 0) {
				markInfoMap.get("userIdMap").put(openId, user.getId());
				userMap.put(user.getId(), user);
			}
			return user.getId();
		} else {
			multiExecutor.execute(new Runnable() {
				@Override
				public void run() {
					JSONObject userInfo = MarkUtils.getUserInfo(access_token,
							openId);
					Long userId = (Long) markInfoMap.get("userIdMap").get(
							openId);
					User user = userMap.get(userId);
					user.setUpdateTime(new Date());
					// user.setCity(userInfo.getString("city"));
					// user.setProvince(userInfo.getString("province"));
					user.setNickname(userInfo.getString("nickname"));
					// user.setGender(userInfo.getString("sex"));
					user.setHeadImgUrl(userInfo.getString("headimgurl"));
					// user.setOpenid(userInfo.getString("openid"));
					UserExample ex = new UserExample();
					ex.createCriteria().andIdEqualTo(userId);
					Integer i = userMapper.updateByExampleSelective(user, ex);
					if (i > 0) {
						markInfoMap.get("userIdMap").put(openId, user.getId());
						userMap.put(user.getId(), user);
					}
				}
			});
			return (Long) markInfoMap.get("userIdMap").get(openId);
		}
	}
}
