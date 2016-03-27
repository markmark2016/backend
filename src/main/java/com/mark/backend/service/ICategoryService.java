package com.mark.backend.service;

import java.util.List;
import java.util.Map;

import com.mark.backend.mysql.po.Category;

public interface ICategoryService {
	/**
	 * 通过参数获取类别列表
	 * 
	 * @param params
	 * @return
	 */
	List<Category> getCategoryList(Map<String, Object> params);

	/**
	 * 设置社群和category的对应关系
	 * 
	 * @param associationId
	 * @param categoryName
	 * @return
	 */
	Long saveCategory(Map<String, Object> params);
}
