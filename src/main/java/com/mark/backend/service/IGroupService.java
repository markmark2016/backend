package com.mark.backend.service;

import java.util.List;

import com.mark.backend.vo.GroupVO;

/**
 * 小组service
 * 
 * @Title:IGroupService
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月14日
 * 
 */
public interface IGroupService {
	/**
	 * 获得全部小组
	 * 
	 * @return
	 */
	List<GroupVO> getAllGroup();

	/**
	 * 根据id获得小组
	 * 
	 * @param id
	 * @return
	 */
	GroupVO getGroupById(Long id);
}
