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
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.IUserService;
import com.mark.backend.vo.GroupUserVO;
import com.mark.backend.vo.GroupVO;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {
	@Resource
	private IGroupService groupService;
	@Resource
	private IUserService userService;

	// 所有小组
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupList(Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<GroupDto> dtoList = groupService.getGroupList(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("msg", "成功");
		map.put("data", dtoList);
		return map;
	}

	// 查看指定小组
	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupById(@PathVariable("groupId") Long groupId,
			@RequestParam(required = true) Long userId, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		params.put("allInfo", "true");
		params.put("groupId", groupId);
		params.put("userId", userId);
		List<GroupDto> dtoList = groupService.getGroupList(params);
		if (dtoList.size() > 0) {
			resultMap.put("status", 1);
			resultMap.put("msg", "成功");
			resultMap.put("data", dtoList.get(0));
		} else {
			resultMap.put("status", 0);
			resultMap.put("msg", "未查到小组信息");
			resultMap.put("data", null);
		}
		return resultMap;
	}

	// 申请小组
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

	// 用户加入小组
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

	// 用户退出小组
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

	// 打卡时用
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupList(@PathVariable(value = "userId") Long userId, Model model) {
		// List<GroupVO> voList = groupService.getUserGroup(userId);
		// model.addAttribute("status", "0");
		// model.addAttribute("data", voList);
		return null;
	}

	// 小组内成员
	@RequestMapping(value = "/{groupId}/users", method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupUserList(@PathVariable(value = "groupId") Long groupId,
			Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		List<UserDto> dtoList = userService.queryUserList(params);
		params.put("status", "1");
		params.put("msg", "sucess");
		params.put("data", dtoList);
		return params;
	}
}
