package com.mark.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IUserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	@Resource
	private IUserService userService;

	/**
	 * 获得用户
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{openId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUserInfo(@PathVariable("openId") String openId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getUserByOpenId(openId);
		map.put("status", 1);
		map.put("data", user);
		return map;
	}

	/**
	 * 删除用户
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{openId}", method = RequestMethod.DELETE)
	public @ResponseBody
	Object deleteUserInfo(@PathVariable("openId") String openId) {
		return null;
	}

	/**
	 * 
	 * @param 更新用户信息
	 * @return
	 */
	@RequestMapping(value = "/{openId}", method = RequestMethod.PUT)
	public @ResponseBody
	Object updateUserInfo(@PathVariable("openId") String openId,
			@RequestBody User user) {
		return null;
	}

}
