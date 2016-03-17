package com.mark.backend.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.mark.backend.utils.MarkUtils;
/**
 * 微信定时获得token
 * @Title:WeixinService
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月17日
 *
 */
@Service
public class WeixinService {
	public static String access_token = null;

	ScheduledExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

	@PostConstruct
	private void init() {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				System.out.println("定时获取Token开始");
			}
		});
		executor.scheduleAtFixedRate(new getTokenSchedule(), 1, 7000,
				TimeUnit.SECONDS);
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
