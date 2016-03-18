package com.mark.backend.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mark.backend.model.Admin;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IBookService;
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.IUserService;
import com.mark.backend.service.impl.WeixinService;
import com.mark.backend.vo.GroupVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource
	private IUserService userService;
	@Resource
	private IGroupService groupService;
	@Resource
	private IBookService bookService;
	@Resource
	private WeixinService wxService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "admin/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Admin admin, Model model) {
		if (admin.getUsername() == "imark" && admin.getPassword() == "root")
			return "admin/index";
		return "redirect:/login";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(Model model) {
		List<User> userList = userService.getUserList();
		model.addAttribute("userlist", userList);
		return "admin/users";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(Model model) {
		return "admin/menu";
	}

	@RequestMapping(value = "/menu/create", method = RequestMethod.POST)
	public String menuCreate(String jsonStr, Model model) {
		String responseStr = wxService.createMenu(jsonStr);
		model.addAttribute("responseStr", responseStr);
		return "admin/menu";
	}

	@RequestMapping(value = "/applygroup", method = RequestMethod.GET)
	public String applyGroup(Model model) {
		List<GroupVO> groupList = groupService.getApplyGroup();
		model.addAttribute("grouplist", groupList);
		return "admin/applygroup";
	}

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String book(Model model) {
		List<Book> bookList = bookService.getBookList();
		model.addAttribute("booklist", bookList);
		return "admin/book";
	}

	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String groups(Model model) {
		List<GroupVO> groupList = groupService.getAllGroup();
		model.addAttribute("grouplist", groupList);
		return "admin/group";
	}
}
