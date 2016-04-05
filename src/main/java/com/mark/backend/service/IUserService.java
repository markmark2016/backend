package com.mark.backend.service;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.GroupDto;
import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.po.User;

public interface IUserService {
	/**
	 * 获取用户详细信息
	 * 
	 * @param openId
	 * @return
	 */
	User getUserByOpenId(String openId);

	/**
	 * 插入新用户
	 * 
	 * @param user
	 * @return
	 */
	int insertUser(User user);

	/**
	 * 根据参数查询用户
	 * 
	 * @param params
	 * @return
	 */
	List<UserDto> queryUserList(Map<String, Object> params);

	/**
	 * 获取所有用户列表
	 * 
	 * @return
	 */
	List<User> getUserList(String nickName);

	/**
	 * 获得用户页信息
	 * 
	 * @param params
	 * @return
	 */
	UserDto queryUserPageInfo(Map<String, Object> params);

	/**
	 * 更新用户详细信息
	 * 
	 * @param openId
	 * @return
	 */
	Integer updateUserDetailInfo(Long userId, User user);

	/**
	 * “我”中，点击小组
	 * 
	 * @param openId
	 * @return
	 */
	Map<String, Object> getUserGroupDetail(Long userId);

	/**
	 * 获取用户已读列表
	 * 
	 * @param userId
	 * @return
	 */
	List<GroupDto> getUserReadedList(Long userId);

	/**
	 * 获得用户全站排名
	 * 
	 * @param userId
	 * @return
	 */
	Integer getUserRank(Long userId);

	/**
	 * 全站排名信息
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> getRankInfo(Long userId);

	/**
	 * 用户在小组的积分信息
	 * 
	 * @param userId
	 * @return
	 */

	Map<String, Object> getUserGroupScoreInfo(Long userId);

	/**
	 * 获得用户咋小组排名
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> getUserGroupRankInfo(Long userId);

	/**
	 * 获得用户咋小组排名详细信息
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	Map<String, Object> getUserGroupRankInfoDetail(Long userId, Long groupId);

	/**
	 * 按照小组的方式获得用户在不同小组中的书评
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> getRemarkInGroupkByUserId(Long userId);

	/**
	 * 获得用户在指定小组的书评
	 * 
	 * @param userId
	 * @param groupid
	 * @return
	 */
	Map<String, Object> getUserRemarkInGroup(Long userId, Long groupId);

	/**
	 * 获得用户打卡数组
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> getPunchDateArray(Map<String, Object> params);

	/**
	 * 根据参数修改用户对书籍的喜好信息
	 * 
	 * @param params
	 * @return
	 */
	Integer editUserBook(Map<String, Object> params);

	User getUserById(Long userId);
}
