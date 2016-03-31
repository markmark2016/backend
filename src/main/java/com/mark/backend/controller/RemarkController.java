package com.mark.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.dto.RemarkDto;
import com.mark.backend.mysql.po.RemarkWithBLOBs;
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.IRemarkService;
import com.mark.backend.utils.Constans;

@Controller
@RequestMapping(value = "/remark")
public class RemarkController {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(RemarkController.class);
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
			@PathVariable("groupId") Long groupId, String pictureUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		remark.setGroupIdFk(groupId);
		remark.setUserIdFk(userId);
		Long remarkId = remarkService.createRemark(remark, pictureUrl);
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
	 * 编辑书评，值针对当天的
	 * 
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	Object updateRemark(RemarkWithBLOBs remark, String pictureUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		// if (remarkId > 0) {
		// map.put("status", 1);
		// map.put("msg", "sucess");
		// } else {
		// map.put("status", 1);
		// map.put("msg", "fail");
		// }
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
			@PathVariable("groupId") Long groupId, String picUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		remark.setGroupIdFk(groupId);
		remark.setUserIdFk(userId);
		Long remarkId = remarkService.createRemark(remark, picUrl);
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
	 * 查看小组内书评
	 * 
	 * @return
	 */
	@RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupRemark(@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> remarkMap = remarkService.getGroupRemark(groupId);
		map.put("status", 1);
		map.put("msg", "sucess");
		map.put("data", remarkMap);
		return map;
	}

	/**
	 * 查看具体书评信息
	 * 
	 * @param remarkId
	 * @return
	 */
	@RequestMapping(value = "/{remarkId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getRemark(@PathVariable("remarkId") Long remarkId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> remarkMap = remarkService.getRemarkById(remarkId);
		map.put("status", 1);
		map.put("msg", "sucess");
		map.put("data", remarkMap);
		return map;
	}

	/**
	 * 对书评点赞
	 * 
	 * @param remarkId
	 * @param userId
	 */
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public @ResponseBody
	Object like(@RequestParam(required = true) Long remarkId,
			@RequestParam(required = true) Long userId,
			@RequestParam(required = true) Long authorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer flag = remarkService.InteractWithRemark(remarkId, userId,
				authorId, null, Constans.LIKE_REMARK);
		if (flag > 0) {
			map.put("status", 1);
			map.put("msg", "sucess");
			LOGGER.info("点赞插入成功,书评ID:" + remarkId + "用户id+" + userId);
		}
		return map;
	}

	/**
	 * 回复书评
	 * 
	 * @param remarkId
	 */
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public @ResponseBody
	Object replyRemark(@RequestParam(required = true) Long remarkId,
			@RequestParam(required = true) Long userId,
			@RequestParam(required = true) Long authorId,
			@RequestParam(required = true) String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer flag = remarkService.InteractWithRemark(remarkId, userId,
				authorId, content, Constans.REPLY_REMARK);
		if (flag > 0) {
			map.put("status", 1);
			map.put("msg", "sucess");
			LOGGER.info("点赞插入成功,书评ID:" + remarkId + "用户id+" + userId);
		}
		return map;
	}

	/**
	 * 获得用户的所有书评 ，如果时间，查询改天内的
	 * 
	 * @param userId
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object userRemarkInDay(@PathVariable("userId") Long userId,
			@RequestParam(required = false) Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		Map<String, Object> resultMap = remarkService.getUserRemarkList(map);
		map.clear();
		map.put("data", resultMap);
		map.put("msg", "success");
		map.put("status", 1);
		return map;
	}
}
