package com.mark.backend.service;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.po.User;

public interface IUserService {
	User getUserByOpenId(String openId);

	int insertUser(User user);

	List<UserDto> queryUserList(Map<String, Object> params);
}
