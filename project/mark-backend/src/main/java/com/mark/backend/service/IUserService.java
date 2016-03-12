package com.mark.backend.service;

import com.mark.backend.mysql.po.User;

public interface IUserService {
	User getUserByOpenId(String openId);

	int insertUser(User user);
}
