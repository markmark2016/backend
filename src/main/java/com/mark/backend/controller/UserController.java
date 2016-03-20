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

	/**
	 * 用户加入的小组
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{openId}/group", method = RequestMethod.GET)
	public @ResponseBody
	Object usersGroup(@PathVariable("openId") String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> ugMap = userService
				.getUserGroupDetail((Long) WeixinService.markInfoMap.get(
						"userIdMap").get(openId));
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
	@RequestMapping(value = "/{openId}/rank", method = RequestMethod.GET)
	public @ResponseBody
	Object usersRank(@PathVariable("openId") String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rankMap = userService
				.getRankInfo((Long) WeixinService.markInfoMap.get("userIdMap")
						.get(openId));
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
	@RequestMapping(value = "/{openId}/rank/{groupId}", method = RequestMethod.GET)
	public @ResponseBody
	Object usersRankInGroup(@PathVariable("openId") String openId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> rankMap = userService.getUserGroupRankInfoDetail(
				(Long) WeixinService.markInfoMap.get("userIdMap").get(openId),
				groupId);
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
	@RequestMapping(value = "/{openId}/score", method = RequestMethod.GET)
	public @ResponseBody
	Object usersScore(@PathVariable("openId") String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> scoreMap = userService
				.getUserGroupScoreInfo((Long) WeixinService.markInfoMap.get(
						"userIdMap").get(openId));
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
	@RequestMapping(value = "/{openId}/punch", method = RequestMethod.GET)
	public @ResponseBody
	Object usersPunch(@PathVariable("openId") String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (true) {
			map.put("status", 1);
			map.put("msg", "更新成功");
		} else {
			map.put("status", 1);
			map.put("msg", "更新失败");
		}
		return map;
	}

	/**
	 * 用户的累计读书
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/{openId}/readed", method = RequestMethod.GET)
	public @ResponseBody
	Object usersReaded(@PathVariable("openId") String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dtoMap = new HashMap<String, Object>();
		List<GroupDto> dtoList = userService
				.getUserReadedList((Long) WeixinService.markInfoMap.get(
						"userIdMap").get(openId));
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
	@RequestMapping(value = "/{openId}/remark", method = RequestMethod.GET)
	public @ResponseBody
	Object usersTotalRemark(@PathVariable("openId") String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (true) {
			map.put("status", 1);
			map.put("msg", "更新成功");
		} else {
			map.put("status", 1);
			map.put("msg", "更新失败");
		}
		return map;
	}

}
