package com.mark.backend.mysql.mapper;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.UserDto;

public interface UserExMapper {
	/**
	 * 按条件查询用户列表
	 * 
	 * @param params
	 * @return
	 */
	public List<UserDto> queryUserList(Map<String, Object> params);
}
