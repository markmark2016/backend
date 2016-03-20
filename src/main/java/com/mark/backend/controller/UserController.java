package com.mark.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IBookService;
import com.mark.backend.service.IUserService;
import com.mark.backend.service.impl.WeixinService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	@Resource
	private IUserService userService;
	@Resource
	private IBookService bookService;

	/**
	 * 获得用户信息
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{openId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUserInfo(@PathVariable("openId") String openId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId",
				WeixinService.markInfoMap.get("userIdMap").get(openId));
		UserDto userDto = userService.queryUserPageInfo(params);
		map.put("status", 1);
		map.put("data", userDto);
		return map;
	}

	/**
	 * 获得用户信息
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/details/{openId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUserDetails(@PathVariable("openId") String openId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId",
				WeixinService.markInfoMap.get("userIdMap").get(openId));
		params.clear();
		Map<String, Object> bookMap = bookService.getUserBookList(params);
		User user = userService.getUserByOpenId(openId);
		params.put("user", user);
		params.put("bookList", bookMap);
		map.put("status", 1);
		map.put("data", params);
		return map;
	}

	/**
	 * 
	 * @param 更新用户信息
	 * @return
	 */
	@RequestMapping(value = "/details/{openId}", method = RequestMethod.PUT)
	public @ResponseBody
	Object updateUserInfo(@PathVariable("openId") String openId, User user) {
		int flag = userService.updateUserDetailInfo(openId, user);
		Map<String, Object> map = new HashMap<String, Object>();
		if (flag > 0) {
			map.put("status", 1);
			map.put("msg", "更新成功");
		} else {
			map.put("status", 1);
			map.put("msg", "更新失败");
		}
		return map;
	}

}
