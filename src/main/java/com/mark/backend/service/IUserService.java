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
	List<User> getUserList();

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
	Integer updateUserDetailInfo(String openId, User user);

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
}
