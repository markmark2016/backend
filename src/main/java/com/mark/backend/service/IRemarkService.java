package com.mark.backend.service;

import java.util.List;

import com.mark.backend.dto.RemarkDto;

public interface IRemarkService {
	/**
	 * 获得用户打卡页列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<RemarkDto> getPunchList(Long userId);
}
