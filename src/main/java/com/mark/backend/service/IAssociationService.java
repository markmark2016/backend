package com.mark.backend.service;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.AssociationDto;
import com.mark.backend.mysql.po.Association;

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

	/**
	 * 获得所有社群信息
	 * 
	 * @param params
	 * @return
	 */
	List<Association> getAllList(Map<String, Object> params);

	/**
	 * 通过id获得社群和她的图片
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> getAssociationById(Long id);

	/**
	 * 插入新纪录
	 * 
	 * @param association
	 * @return
	 */
	Integer editAssociation(Association association, String pictureUrl);

	Integer deleteByAssociationId(Long associationId);
}
