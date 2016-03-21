package com.mark.backend.mysql.mapper;

import java.util.List;

import com.mark.backend.dto.RemarkDto;

public interface RemarkExMapper {
	/**
	 * 获得用户打卡页列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<RemarkDto> getUserPunchList(Long userId);
}
