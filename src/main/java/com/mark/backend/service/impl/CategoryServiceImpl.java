package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.CategoryMapper;
import com.mark.backend.mysql.po.Category;
import com.mark.backend.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> getCategoryList(Map<String, Object> params) {
		Long associationId = (Long) params.get("associationId");
		List<Category> list = new ArrayList<Category>();
		if (associationId != null) {
			list = categoryMapper.selectByParams(params);
		}
		// TODO Auto-generated method stub
		return list;
	}

}
