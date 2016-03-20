package com.mark.backend.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.mapper.UserExMapper;
import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserExample;
import com.mark.backend.service.IUserService;
import com.mark.backend.utils.MarkUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private UserExMapper uexMapper;

	@Override
	public User getUserByOpenId(String openId) {
		UserExample ue = new UserExample();
		ue.createCriteria().andOpenidEqualTo(openId);
		User u = userMapper.selectByExample(ue).get(0);
		return u;
	}

	@Override
	public int insertUser(User user) {
		return userMapper.insert(user);
	}

	@Override
	public List<UserDto> queryUserList(Map<String, Object> params) {
		List<UserDto> dtoList = uexMapper.queryUserList(params);
		return dtoList;
	}

	@Override
	public List<User> getUserList() {
		UserExample ue = new UserExample();
		List<User> userList = userMapper.selectByExample(ue);
		return userList;
	}

	@Override
	public UserDto queryUserPageInfo(Map<String, Object> params) {
		UserDto dto = uexMapper.queryUserPageInfo(params);
		return dto;
	}

	@Override
	public Integer updateUserDetailInfo(String openId, User user) {
		UserExample ex = new UserExample();
		ex.createCriteria().andOpenidEqualTo(openId);
		user.setUpdateTime(MarkUtils.getCurrentTime());
		int i = userMapper.updateByExampleSelective(user, ex);
		return i;
	}
}
