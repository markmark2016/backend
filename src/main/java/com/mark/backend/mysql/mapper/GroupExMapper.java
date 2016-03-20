package com.mark.backend.mysql.mapper;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.GroupDto;

public interface GroupExMapper {
	/**
	 * 获得所有小组列表
	 * 
	 * @param params
	 * @return
	 */
	public List<GroupDto> queryGroupList(Map<String, Object> params);

	/**
	 * 获得用户的小组列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<GroupDto> getUserGroupList(Long userId);

	/**
	 * 获得小组的所有在读书友
	 * 
	 * @param groupId
	 * @return
	 */
	public Integer getTotalReader(Long groupId);
}
