package com.mark.backend.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mark.backend.dto.AssociationDto;
import com.mark.backend.dto.GroupDto;
import com.mark.backend.mysql.mapper.AssociationExMapper;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.service.IAssociationService;

@Service
public class AssociationServiceImpl implements IAssociationService {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(AssociationServiceImpl.class);

	@Resource
	private AssociationExMapper aexMapper;
	@Resource
	private GroupExMapper gexMapper;

	@Override
	public List<AssociationDto> getAssociationList(Map<String, Object> params) {
		List<AssociationDto> resultList = aexMapper
				.queryAssociationList(params);
		return resultList;

	}

	@Override
	public AssociationDto getAssociationById(Map<String, Object> params) {
		List<AssociationDto> resultList = aexMapper
				.queryAssociationList(params);
		AssociationDto dto = new AssociationDto();
		if (resultList.size() > 0) {
			dto = resultList.get(0);
			List<GroupDto> groupList = gexMapper.queryGroupList(params);
			dto.setGroupList(groupList);
		} else {
			LOGGER.error("所查询的小组不存在,id:" + params.get("associationId"));
		}
		return dto;
	}
}
