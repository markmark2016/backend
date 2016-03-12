package com.mark.backend.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private IUserService userService;

	@RequestMapping(value = "/info/get/{openId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUserInfo(@PathVariable("openId") String openId,
			HttpServletResponse response) throws IOException {
		User user = userService.getUserByOpenId(openId);
		System.out.println(JSON.toJSON(user));
		// response.getWriter().print(JSON.toJSON(user));
		return user;
	}
}
