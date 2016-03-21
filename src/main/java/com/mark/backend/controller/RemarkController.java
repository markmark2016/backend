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
import com.mark.backend.mysql.po.Remark;
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.IRemarkService;
import com.mark.backend.service.impl.WeixinService;

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
	@RequestMapping(value = "/punch/{openId}", method = RequestMethod.GET)
	public @ResponseBody
	Object punchIndex(@PathVariable("openId") String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RemarkDto> list = remarkService
				.getPunchList((Long) WeixinService.markInfoMap.get("userIdMap")
						.get(openId));
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
	@RequestMapping(value = "/create/{groupId}/{openId}", method = RequestMethod.POST)
	public @ResponseBody
	Object createRemark(Remark remark, @PathVariable("openId") String openId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		remark.setGroupIdFk(groupId);
		remark.setUserIdFk((Long) WeixinService.markInfoMap.get("userIdMap")
				.get(openId));
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
	@RequestMapping(value = "/complete/{groupId}/{openId}", method = RequestMethod.POST)
	public @ResponseBody
	Object completeBook(Remark remark, @PathVariable("openId") String openId,
			@PathVariable("groupId") Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		remark.setGroupIdFk(groupId);
		remark.setUserIdFk((Long) WeixinService.markInfoMap.get("userIdMap")
				.get(openId));
		Long remarkId = remarkService.createRemark(remark);
		Integer updateFlag = groupService.updateGroupUserStatus(groupId,
				(Long) WeixinService.markInfoMap.get("userIdMap").get(openId),
				"2");
		if (remarkId > 0 && updateFlag > 0) {
			map.put("status", 1);
			map.put("msg", "sucess");
		} else {
			map.put("status", 1);
			map.put("msg", "fail");
		}
		return map;
	}
}
