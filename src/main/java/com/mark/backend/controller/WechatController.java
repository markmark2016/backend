package com.mark.backend.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.model.CheckModel;
import com.mark.backend.service.impl.WeixinService;
import com.mark.backend.utils.MarkUtils;

@Controller
@RequestMapping(value = "/wechat")
public class WechatController {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(WechatController.class);
	@Resource
	private WeixinService wxService;

	/**
	 * 微信验证
	 * 
	 * @param checkModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	String validate(CheckModel checkModel, Model model) {
		String result = MarkUtils.validateWechatInfo(checkModel);
		System.out.println(result.equals(checkModel.getEchostr()));
		return result;
	}

	@RequestMapping(value = "/authorize", method = RequestMethod.GET)
	public Object authorize(@RequestParam(required = true) String code,
			@RequestParam(required = false) String status,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = wxService.getUserInfo(code, status).toString();
		LOGGER.info("用户:" + userId + "登录了");
		Cookie c = new Cookie("userId", userId);
		c.setDomain("*");
		c.setPath("/");
		response.addCookie(c);
		response.setHeader("markUserId", userId);
		// response.setHeader("Location",
		// "http://markmark.sinaapp.com/app/#/tab/groups-center");
		try {
			response.sendRedirect("http://www.swanhi.com/app/" + "?userId="
					+ userId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
