package com.mark.backend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
