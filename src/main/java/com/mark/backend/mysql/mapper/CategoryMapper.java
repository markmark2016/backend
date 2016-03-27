package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.Category;
import com.mark.backend.mysql.po.CategoryExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
	int countByExample(CategoryExample example);

	int deleteByExample(CategoryExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Category record);

	int insertSelective(Category record);

	List<Category> selectByExample(CategoryExample example);

	Category selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Category record,
			@Param("example") CategoryExample example);

	int updateByExample(@Param("record") Category record,
			@Param("example") CategoryExample example);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);

	/**
	 * 根据参数查询category
	 * 
	 * @param params
	 * @return
	 */
	List<Category> selectByParams(Map<String, Object> params);
}