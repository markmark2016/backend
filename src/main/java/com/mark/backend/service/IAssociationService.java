package com.mark.backend.service;

import java.util.List;

import com.mark.backend.vo.AssociationVO;

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
	List<AssociationVO> getAssociationList();

	/**
	 * 根据社群主键获得社群与小组的信息
	 * 
	 * @param id
	 * @return
	 */
	AssociationVO getAssociationById(Long id);
}
