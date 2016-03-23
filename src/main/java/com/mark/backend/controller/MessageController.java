package com.mark.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.service.impl.MessageService;

@Controller
@RequestMapping(value = "/msg")
public class MessageController {

	@Resource
	private MessageService msgService;

	/**
	 * 获得用户信息
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUserInfo(@PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		map.put("status", 1);
		// map.put("data", userDto);
		return map;
	}
}
