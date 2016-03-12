package com.mark.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserExample;
import com.mark.backend.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

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
}
