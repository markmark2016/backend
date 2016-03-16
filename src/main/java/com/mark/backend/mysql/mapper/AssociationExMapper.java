package com.mark.backend.mysql.mapper;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.AssociationDto;

public interface AssociationExMapper {
	public List<AssociationDto> queryAssociationList(Map<String, Object> params);
}
