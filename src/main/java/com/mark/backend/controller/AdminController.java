package com.mark.backend.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mark.backend.model.Admin;
import com.mark.backend.mysql.po.Association;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.service.IAssociationService;
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.impl.WeixinService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource
	private IAssociationService associationService;
	@Resource
	private WeixinService wxService;
	@Resource
	private IGroupService groupService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "admin/dashboard";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Admin admin, Model model) {
		if (admin.getUsername() == "imark" && admin.getPassword() == "root")
			return "admin/index";
		return "redirect:/login";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(Model model) {
		return "admin/users";
	}

	@RequestMapping(value = "/association", method = RequestMethod.GET)
	public String associationList(Model model) {
		List<Association> list = associationService.getAllList(null);
		model.addAttribute("list", list);
		return "admin/association";
	}

	@RequestMapping(value = "/association/edit", method = RequestMethod.GET)
	public String association(Model model, Long associationId) {
		if (associationId != null) {
			Association association = associationService
					.getAssociationById(associationId);
			model.addAttribute("association", association);
		}
		return "admin/association_edit";
	}

	@RequestMapping(value = "/association/save", method = RequestMethod.POST)
	public String associationSave(Model model, Association association) {
		Integer i = associationService.editAssociation(association);
		return "admin/association";
	}

	@RequestMapping(value = "/menu/create", method = RequestMethod.POST)
	public String menuCreate(String jsonStr, Model model) {
		String responseStr = wxService.createMenu(jsonStr);
		model.addAttribute("responseStr", responseStr);
		return "admin/menu";
	}

	@RequestMapping(value = "/applygroup", method = RequestMethod.GET)
	public String applyGroup(Model model) {
		return "admin/applygroup";
	}

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String book(Model model) {
		return "admin/book";
	}

	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String groups(Model model) {
		List<Group> groupList = groupService.getAllGroup(null);
		model.addAttribute("groupList", groupList);
		return "admin/group";
	}

	@RequestMapping(value = "/group/edit", method = RequestMethod.GET)
	public String editGroup(Model model, Long groupId) {
		if (groupId != null) {
			Group group = groupService.getGroupInfo(groupId);
			model.addAttribute(group);
		}
		return "admin/group_edit";
	}

	@RequestMapping(value = "/group/save", method = RequestMethod.POST)
	public String editGroup(Model model, Group group) {
		int i = groupService.saveGroup(group);
		return "admin/group";
	}
}
