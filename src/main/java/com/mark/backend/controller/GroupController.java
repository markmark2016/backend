package com.mark.backend.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.service.IGroupService;
import com.mark.backend.vo.GroupVO;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
	@Resource
	private IGroupService groupService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Object getUserInfo(Model model) {
		List<GroupVO> voList = groupService.getAllGroup();
		model.addAttribute("status", "0");
		model.addAttribute("data", voList);
		return model;
	}

}
