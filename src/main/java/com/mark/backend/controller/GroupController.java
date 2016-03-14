package com.mark.backend.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.service.IGroupService;
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

}
