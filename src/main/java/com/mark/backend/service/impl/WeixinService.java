package com.mark.backend.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import com.mark.backend.model.UserInfoAccessToken;
import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserExample;
import com.mark.backend.utils.MarkUtils;

/**
 * 微信定时获得token
 *
 * @author YangTianxiao
 * @Title:WeixinService
 * @Description:TODO
 * @date 2016年3月17日
 */
@Service
public class WeixinService {
    public static String access_token = null;

    public static String ticket = null;

    public static String auth_access_token = null;

    public static String refresh_auth_access_token = null;

    // public static Map<String, Map<String, Object>> markInfoMap = new HashMap<String, Map<String, Object>>();

    public static Map<String, User> openIdUserMap = new ConcurrentHashMap<>();

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
        UserExample ex = new UserExample();
        ex.createCriteria();
        List<User> userList = userMapper.selectByExample(ex);
        if (userList != null) {
            for (User user : userList) {
                userMap.put(user.getId(), user);
                String openId = user.getOpenid();
                if ("dirty openid data".equals(openId)) {
                    continue;
                } else {
                    openIdUserMap.put(openId, user);
                }

            }
        }
    }

    /**
     * 获得用户openid和id 对应map
     *
     * @return
     */
  /*  private Map<String, Object> getUserIdMap() {
        UserExample ex = new UserExample();
        ex.createCriteria();
        List<User> userList = userMapper.selectByExample(ex);
        for (User user : userList) {
            userMap.put(user.getId(), user);
            openIdUserMap.put(user.getOpenid(), user);
        }
    }*/

    class getTokenSchedule implements Runnable {
        @Override
        public void run() {

            access_token = MarkUtils.getAccessToken().getAccess_token();
            ticket = MarkUtils.getJSTicket(access_token);
            while (access_token == null) {
                access_token = MarkUtils.getAccessToken().getAccess_token();
            }
            System.out.println("GOT_GLOBAL_ACCESS_TOKEN:" + access_token);
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

    public Object getUserInfo(String code, String state) {

        System.out.println("Getting user info, code:" + code + "state:" + state);

        /**
         * 根据获得的code去得到用户的openid
         */
        UserInfoAccessToken userInfoAccessToken = MarkUtils.getAuthJsonobject(code);

        System.out.println("Got auth json object done, UserInfoAccessToken:" + userInfoAccessToken);

        if (userInfoAccessToken == null) {
            return null;
        }

        String openId = userInfoAccessToken.getOpenid();
        String scope = userInfoAccessToken.getScope();
        String accessToken = userInfoAccessToken.getAccess_token();

        JSONObject userInfo;

        if (openId == null || accessToken == null) {
            return null;
        }

        if (scope != null && "snsapi_base".equals(scope)) {
            userInfo = MarkUtils.getUserInfo(access_token, openId);
        } else {
            userInfo = MarkUtils.getSNSUserInfo(accessToken, openId);
        }

        System.out.println("Got userInfo by access_token and openid done! userInfo:" + userInfo);

        if (userInfo == null) {
            return null;
        }

        /**
         * 查看用户是否已存在mark数据库中
         */
        Integer errCode = userInfo.getInteger("errcode");
        if (errCode != null && errCode == 40001) {
            System.out.println("Got user info failure!");
            if (openIdUserMap.containsKey(openId)) {
                return openIdUserMap.get(openId).getId();
            }
            return null;
        }

        String nickName = userInfo.getString("nickname");
        String city = userInfo.getString("city");
        String province = userInfo.getString("province");
        Integer sex = userInfo.getInteger("sex");
        String headImgUrl = userInfo.getString("headimgurl");

        if (!openIdUserMap.containsKey(openId)) {

            User user = new User();
            user.setCity(city);
            user.setProvince(province);
            user.setCreateTime(MarkUtils.getCurrentTime());
            user.setUpdateTime(MarkUtils.getCurrentTime());
            user.setGender(sex);
            user.setHeadImgUrl(headImgUrl);
            user.setNickname(nickName);
            user.setOpenid(openId);

            System.out.println("User not exists, now creating, user:" + user);

            int i = userMapper.insert(user);
            if (i > 0) {
                userMap.put(user.getId(), user);
                openIdUserMap.put(openId, user);
                return user.getId();
            }
        } else {
            Long userId = openIdUserMap.get(openId).getId();
            User userInDb = userMapper.selectByPrimaryKey(userId);
            if (userInDb == null) {
                throw new IllegalStateException("User information in cache map didn't match db! userId:" + userId +
                        ", openid:" + openId);
            }
            if (nickName != null) {
                userInDb.setNickname(nickName);
            }
            if (headImgUrl != null) {
                userInDb.setHeadImgUrl(headImgUrl);
            }
            if (sex != null) {
                userInDb.setGender(sex);
            }
            if (userInDb != null) {
                userInDb.setCity(city);
            }
            if (province != null) {
                userInDb.setProvince(province);
            }
            userInDb.setUpdateTime(MarkUtils.getCurrentTime());

            System.out.println("User already exists, now updating, user:" + userInDb);

            int i = userMapper.updateByPrimaryKey(userInDb);
            if (i > 0) {
                userMap.put(userId, userInDb);
                openIdUserMap.put(openId, userInDb);
                return userId;
            }
        }
        return null;
    }
}
