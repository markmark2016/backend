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

import com.mark.backend.dto.AssociationDto;
import com.mark.backend.service.IAssociationService;

@Controller
@RequestMapping(value = "/associations")
public class AssociationController {

	@Resource
	private IAssociationService associaService;

	// 社群列表
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Object getGroupList(Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<AssociationDto> voList = associaService.getAssociationList(params);
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
			@RequestParam(required = true) Long userId, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("associationId", associationId);
		params.put("userId", userId);
		AssociationDto dto = associaService.getAssociationById(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("msg", "成功");
		map.put("userId", userId);
		map.put("data", dto);
		return map;
	}
}
