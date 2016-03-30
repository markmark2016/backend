package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.AssociationCategoryMapper;
import com.mark.backend.mysql.mapper.CategoryMapper;
import com.mark.backend.mysql.po.AssociationCategory;
import com.mark.backend.mysql.po.AssociationCategoryExample;
import com.mark.backend.mysql.po.Category;
import com.mark.backend.service.ICategoryService;
import com.mark.backend.utils.MarkUtils;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Resource
	private CategoryMapper categoryMapper;
	@Resource
	private AssociationCategoryMapper acMapper;

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

	@Override
	public Long saveCategory(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Long associationId = (Long) params.get("associationId");
		Long categoryId = (Long) params.get("categoryId");
		String categoryName = params.get("categoryName").toString();
		// 社群类别关系表该标记
		Long acTag = 0L;
		// 类别表标记
		Long cTag = 0L;
		if (categoryId != null) {
			Category c = new Category();
			c.setCategoryName(categoryName);
			c.setId(categoryId);
			cTag = (long) categoryMapper.updateByPrimaryKeySelective(c);
		} else {
			// 插入新纪录
			Category c = new Category();
			c.setCreateTime(MarkUtils.getCurrentTime());
			c.setCategoryName(categoryName);
			c.setStatus("1");
			// 插入的新纪录的id
			categoryMapper.insert(c);
			cTag = c.getId();
			if (cTag > 0) {
				AssociationCategory ac = new AssociationCategory();
				ac.setAssociationIdFk(associationId);
				ac.setCategoryIdFk(cTag);
				ac.setCreateTime(MarkUtils.getCurrentTime());
				ac.setStatus("1");
				acMapper.insert(ac);
				acTag = ac.getId();
			}
		}
		return acTag + cTag;
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		Category category = categoryMapper.selectByPrimaryKey(categoryId);
		return category;
	}

	@Override
	public Integer deleteByCategoryId(Long categoryId) {
		int i = categoryMapper.deleteByPrimaryKey(categoryId);
		AssociationCategoryExample ex = new AssociationCategoryExample();
		ex.createCriteria().andCategoryIdFkEqualTo(categoryId);
		acMapper.deleteByExample(ex);
		return i;
	}

}
