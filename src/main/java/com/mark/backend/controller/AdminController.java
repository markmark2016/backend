package com.mark.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mark.backend.model.Admin;
import com.mark.backend.mysql.po.Association;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.service.IAssociationService;
import com.mark.backend.service.IBookService;
import com.mark.backend.service.ICloudUploadService;
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.impl.WeixinService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource
	private WeixinService wxService;
	@Resource
	private IGroupService groupService;
	@Resource
	private IBookService bookService;
	@Resource
	private IAssociationService associationService;
	@Resource
	private ICloudUploadService uploadService;

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

	/** ---------------------社群相关controller------------------------ */
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
		model.addAttribute("count", i);
		return "admin/association";
	}

	/** ---------------------修改微信按钮相关controller------------------------ */
	@RequestMapping(value = "/menu/create", method = RequestMethod.POST)
	public String menuCreate(String jsonStr, Model model) {
		String responseStr = wxService.createMenu(jsonStr);
		model.addAttribute("responseStr", responseStr);
		return "admin/menu";
	}

	/** ---------------------申请相关controller------------------------ */
	@RequestMapping(value = "/applygroup", method = RequestMethod.GET)
	public String applyGroup(Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询申请状态的小组
		params.put("status", "applygroup");
		List<Group> groupList = groupService.getAllGroup(params);
		model.addAttribute("groupList", groupList);
		// 显示通过按钮
		model.addAttribute("apply", "apply");
		return "admin/group";
	}

	/** ---------------------书籍相关controller------------------------ */
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String book(Model model, String bookName) {
		List<Book> bookList = bookService.getBookList(bookName);
		model.addAttribute("bookList", bookList);
		return "admin/book";
	}

	@RequestMapping(value = "/book/search", method = RequestMethod.POST)
	public String bookSearch(Model model, String bookName) {
		List<Book> bookList = bookService.getBookList(bookName);
		model.addAttribute("bookList", bookList);
		return "admin/book";
	}

	@RequestMapping(value = "/book/edit", method = RequestMethod.GET)
	public String bookEdit(Model model, Long bookId) {
		if (bookId != null) {
			Book book = bookService.getBookById(bookId);
			model.addAttribute("book", book);
		}
		return "/admin/book_edit";
	}

	@RequestMapping(value = "/book/save", method = RequestMethod.POST)
	public String bookSave(Model model, Book book) {
		Integer i = bookService.saveBook(book);
		return "redirect:/admin/book";
	}

	/** ---------------------小组相关controller------------------------ */
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String groups(Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询申请状态的小组
		params.put("status", "group");
		List<Group> groupList = groupService.getAllGroup(params);
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
		return "redirect:/admin/group";
	}

	@RequestMapping(value = "/group/approve", method = RequestMethod.GET)
	public String approveGroup(Model model, Group group) {
		int i = groupService.saveGroup(group);
		return "redirect:/admin/group";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	Object upload(MultipartFile picture) {
		Map<String, Object> map = new HashMap<String, Object>();
		String picUrl = "";
		if (picture != null) {
			picUrl = uploadService.upload(picture, null);
		}
		if (picUrl != null) {
			map.put("status", "true");
			map.put("pictureUrl", picUrl);
		} else {
			map.put("status", "false");
		}
		return map;
	}

}
