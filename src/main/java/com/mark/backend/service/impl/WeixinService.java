package com.mark.backend.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public static Map<String, Map<String, String>> markInfoMap = new HashMap<String, Map<String, String>>();

	@Resource
	private UserMapper userMapper;

	ScheduledExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

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
		Map<String, String> userIdMap = getUserIdMap();
		markInfoMap.put("userIdMap", userIdMap);
	}

	/**
	 * 获得用户openid和id 对应map
	 * 
	 * @return
	 */
	private Map<String, String> getUserIdMap() {
		UserExample ex = new UserExample();
		ex.createCriteria();
		List<User> userList = userMapper.selectByExample(ex);
		Map<String, String> map = new HashMap<String, String>();
		for (User user : userList) {
			map.put(user.getOpenid(), user.getId().toString());
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
}
