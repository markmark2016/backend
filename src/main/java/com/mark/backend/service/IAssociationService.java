package com.mark.backend.service;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.AssociationDto;

/**
 * 社群service
 * 
 * @Title:IAssociationService
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月14日
 * 
 */
public interface IAssociationService {
	/**
	 * 获得所有社群
	 * 
	 * @return
	 */
	List<AssociationDto> getAssociationList(Map<String, Object> params);

	/**
	 * 根据社群主键获得社群与小组的信息
	 * 
	 * @param id
	 * @return
	 */
	AssociationDto getAssociationById(Map<String, Object> params);
}
