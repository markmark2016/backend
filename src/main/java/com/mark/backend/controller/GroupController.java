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

import com.mark.backend.service.IGroupService;
import com.mark.backend.vo.GroupUserVO;
import com.mark.backend.vo.GroupVO;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {
	@Resource
	private IGroupService groupService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupList(Model model) {
		List<GroupVO> voList = groupService.getAllGroup();
		model.addAttribute("status", "0");
		model.addAttribute("data", voList);
		return model;
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupById(@PathVariable("groupId") Long groupId, Model model) {
		GroupVO vo = groupService.getGroupById(groupId);
		model.addAttribute("status", "0");
		model.addAttribute("data", vo);
		return model;
	}

	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public @ResponseBody
	Object applyGroup(GroupVO vo, Model model) {
		Long flag = groupService.createGroup(vo);
		Map<String, String> map = new HashMap<String, String>();
		if (flag > 0) {
			map.put("status", "1");
			map.put("msg", "申请成功");
		} else {
			map.put("status", "0");
			map.put("msg", "申请失败");
		}
		return map;
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public @ResponseBody
	Object joinGroup(GroupUserVO vo, Model model) {
		Long joinFlag = groupService.joinGroup(vo.getGroupIdFk(),
				vo.getUserIdFk(), "0");
		Map<String, String> map = new HashMap<String, String>();
		if (joinFlag > 0) {
			map.put("status", "1");
			map.put("msg", "已加入");
		} else {
			map.put("status", "0");
			map.put("msg", "加入失败");
		}
		return map;
	}

	@RequestMapping(value = "/quit", method = RequestMethod.PUT)
	public @ResponseBody
	Object quitGroup(GroupUserVO vo, Model model) {
		Integer quitFlag = groupService.quitGroup(vo.getGroupIdFk(),
				vo.getUserIdFk());
		Map<String, String> map = new HashMap<String, String>();
		if (quitFlag > 0) {
			map.put("status", "1");
			map.put("msg", "已退出");
		} else {
			map.put("status", "0");
			map.put("msg", "退出失败");
		}
		return map;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupList(@PathVariable(value = "userId") Long userId, Model model) {
		List<GroupVO> voList = groupService.getUserGroup(userId);
		model.addAttribute("status", "0");
		model.addAttribute("data", voList);
		return model;
	}
}
