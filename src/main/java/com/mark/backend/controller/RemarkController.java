package com.mark.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.dto.RemarkDto;
import com.mark.backend.mysql.po.RemarkWithBLOBs;
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.IRemarkService;

@Controller
@RequestMapping(value = "/remark")
public class RemarkController {

	@Resource
	private IRemarkService remarkService;
	@Resource
	private IGroupService groupService;

	/**
	 * 打卡页列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/punch/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object punchIndex(@PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RemarkDto> list = remarkService.getPunchList(userId);
		if (list.size() > 0) {
			map.put("status", 1);
			map.put("msg", "sucess");
			map.put("data", list);
		} else {
			map.put("status", 0);
			map.put("msg", "暂无信息");
		}
		return map;
	}

	/**
	 * 提交书评
	 * 
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "/create/{groupId}/{userId}", method = RequestMethod.POST)
	public @ResponseBody
	Object createRemark(RemarkWithBLOBs remark,
			@PathVariable("userId") Long userId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		remark.setGroupIdFk(groupId);
		remark.setUserIdFk(userId);
		Long remarkId = remarkService.createRemark(remark);
		if (remarkId > 0) {
			map.put("status", 1);
			map.put("msg", "sucess");
		} else {
			map.put("status", 1);
			map.put("msg", "fail");
		}
		return map;
	}

	/**
	 * 读完一本书
	 * 
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "/complete/{groupId}/{userId}", method = RequestMethod.POST)
	public @ResponseBody
	Object completeBook(RemarkWithBLOBs remark,
			@PathVariable("userId") Long userId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		remark.setGroupIdFk(groupId);
		remark.setUserIdFk(userId);
		Long remarkId = remarkService.createRemark(remark);
		Integer updateFlag = groupService.updateGroupUserStatus(groupId,
				userId, "2");
		if (remarkId > 0 && updateFlag > 0) {
			map.put("status", 1);
			map.put("msg", "sucess");
		} else {
			map.put("status", 1);
			map.put("msg", "fail");
		}
		return map;
	}

	/**
	 * 查看今日书评
	 * 
	 * @return
	 */
	@RequestMapping(value = "/today/{groupId}/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object checkTodayRemark(@PathVariable("userId") Long userId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> remarkMap = remarkService
				.getUserInGroupTodayRemark(userId, groupId);
		map.put("status", 1);
		map.put("msg", "sucess");
		map.put("data", remarkMap);
		return map;
	}

	/**
	 * 查看今日书评
	 * 
	 * @return
	 */
	@RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
	public @ResponseBody
	Object checkTodayRemark(@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// Map<String, Object> remarkMap = remarkService
		// .getUserInGroupTodayRemark(openId, groupId);
		map.put("status", 1);
		map.put("msg", "sucess");
		// map.put("data", remarkMap);
		return map;
	}
}
