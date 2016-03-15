package com.mark.backend.mysql.mapper;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.GroupDto;

public interface GroupExMapper {
	public List<GroupDto> queryGroupList(Map<String, Object> queryParam);
}
