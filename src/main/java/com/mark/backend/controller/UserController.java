package com.mark.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.dto.GroupDto;
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
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUserInfo(@PathVariable("userId") Long userId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
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
	@RequestMapping(value = "/details/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUserDetails(@PathVariable("userId") Long userId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.clear();
		Map<String, Object> bookMap = bookService.getUserBookList(params);
		User user = WeixinService.userMap.get(userId);
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
	@RequestMapping(value = "/details/{userId}", method = RequestMethod.PUT)
	public @ResponseBody
	Object updateUserInfo(@PathVariable("userId") Long userId, User user) {
		int flag = userService.updateUserDetailInfo(userId, user);
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

	/**
	 * 用户加入的小组
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/group", method = RequestMethod.GET)
	public @ResponseBody
	Object usersGroup(@PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> ugMap = userService.getUserGroupDetail(userId);
		map.put("status", 1);
		map.put("msg", "success");
		map.put("data", ugMap);
		return map;
	}

	/**
	 * 用户的排名
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/rank", method = RequestMethod.GET)
	public @ResponseBody
	Object usersRank(@PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rankMap = userService.getRankInfo(userId);
		map.put("status", 1);
		map.put("msg", "sucess");
		map.put("data", rankMap);
		return map;
	}

	/**
	 * 用户在小组内的具体排名
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/rank/{groupId}", method = RequestMethod.GET)
	public @ResponseBody
	Object usersRankInGroup(@PathVariable("userId") Long userId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rankMap = userService.getUserGroupRankInfoDetail(
				userId, groupId);
		map.put("status", 1);
		map.put("msg", "sucess");
		map.put("data", rankMap);
		return map;
	}

	/**
	 * 用户的积分
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/score", method = RequestMethod.GET)
	public @ResponseBody
	Object usersScore(@PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> scoreMap = userService
				.getUserGroupScoreInfo(userId);
		map.put("status", 1);
		map.put("msg", "success");
		map.put("data", scoreMap);
		return map;
	}

	/**
	 * 用户的打卡
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/punch", method = RequestMethod.GET)
	public @ResponseBody
	Object usersPunch(@PathVariable("userId") Long userId,
			@RequestParam(required = false) Long startDate,
			@RequestParam(required = false) Long endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		// 直接用参数map返回data
		params = userService.getPunchDateArray(params);
		map.put("status", 1);
		map.put("msg", "更新成功");
		map.put("data", params);
		return map;
	}

	/**
	 * 用户的累计读书
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/readed", method = RequestMethod.GET)
	public @ResponseBody
	Object usersReaded(@PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dtoMap = new HashMap<String, Object>();
		List<GroupDto> dtoList = userService.getUserReadedList(userId);
		dtoMap.put("groupList", dtoList);
		dtoMap.put("totalRead", dtoList.size());
		if (dtoList.size() > 0) {
			map.put("status", 1);
			map.put("msg", "success");
			map.put("data", dtoMap);
		} else {
			map.put("status", 0);
			map.put("msg", "无已读书籍");
		}
		return map;
	}

	/**
	 * 用户的累计书评
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/remark", method = RequestMethod.GET)
	public @ResponseBody
	Object usersTotalRemark(@PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = userService
				.getRemarkInGroupkByUserId(userId);
		map.put("status", 1);
		map.put("msg", "更新成功");
		map.put("date", data);
		return map;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/remark/{groupId}", method = RequestMethod.GET)
	public @ResponseBody
	Object userRemarkInGroup(@PathVariable("userId") Long userId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = userService.getUserRemarkInGroup(userId,
				groupId);
		map.put("status", 1);
		map.put("msg", "更新成功");
		map.put("date", data);
		return map;
	}

}
