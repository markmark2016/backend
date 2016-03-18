package com.mark.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mark.backend.model.Admin;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Admin admin, Model model) {
		if (admin.getUsername() == "imark" && admin.getPassword() == "root")
			return "admin/index";
		return "redirect:/admin";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "admin/index";
	}
}
