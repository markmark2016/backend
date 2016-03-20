package com.mark.backend.service;

import java.util.List;

import com.mark.backend.dto.UserDto;

public interface IScoreService {
	/**
	 * 全站排行榜
	 * 
	 * @return
	 */
	public List<UserDto> getUserRankList();
}
