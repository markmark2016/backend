package com.mark.backend.service;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.GroupDto;
import com.mark.backend.mysql.po.Group;
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
	List<Group> getAllGroup(Map<String, Object> params);

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

	/**
	 * 根据参数查询小组
	 * 
	 * @param params
	 * @return
	 */
	List<GroupDto> getGroupList(Map<String, Object> params);

	List<GroupVO> getApplyGroup();

	List<GroupDto> getUserGroupList(Long userId);

	/**
	 * 更新用户在小组中的状态
	 * 
	 * @return
	 */
	Integer updateGroupUserStatus(Long groupId, Long userId, String status);

	/**
	 * 获得小组
	 * 
	 * @param groupId
	 * @return
	 */
	Group getGroupInfo(Long groupId);

	/**
	 * 根据参数去存储group
	 * 
	 * @param params
	 * @return
	 */
	Integer saveGroup(Map<String, Object> params);

}
