package com.mark.backend.mysql.mapper;

import java.util.List;

import com.mark.backend.dto.InteractDto;
import com.mark.backend.dto.UserDto;

public interface InteractExMapper {
	/**
	 * 获得书评的点赞列表
	 * 
	 * @param remarkId
	 * @return
	 */
	public List<UserDto> getLikeList(Long remarkId);

	/**
	 * 获得书评的回复列表
	 * 
	 * @param remarkId
	 * @return
	 */
	public List<InteractDto> getReplyList(Long remarkId);
}
