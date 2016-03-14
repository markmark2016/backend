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
	 * 创建新小组
	 * 
	 * @param vo
	 * @return
	 */
	Long createGroup(GroupVO vo);

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

	/**
	 * 用户加入吓阻
	 * 
	 * @param groupId
	 * @param userId
	 * @return
	 */
	Long joinGroup(Long groupId, Long userId, String clazz);

	/**
	 * 用户退出小组
	 * 
	 * @param groupId
	 * @param userId
	 * @return
	 */
	Integer quitGroup(Long groupId, Long userId);

	List<GroupVO> getUserGroup(Long userId);
}
