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

import com.mark.backend.service.IAssociationService;
import com.mark.backend.vo.AssociationVO;

@Controller
@RequestMapping(value = "/associations")
public class AssociationController {

	@Resource
	private IAssociationService associaService;

	// 社群列表
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupList(Model model) {
		List<AssociationVO> voList = associaService.getAssociationList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("msg", "成功");
		map.put("data", voList);
		return map;
	}

	// 查看社群详情
	@RequestMapping(value = "/{associationId}", method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupList(@PathVariable("associationId") Long associationId,
			Model model) {
		AssociationVO voList = associaService.getAssociationById(associationId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("msg", "成功");
		map.put("data", voList);
		return map;
	}
}
