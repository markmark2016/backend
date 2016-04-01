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

	/**
	 * 
	 * @param userId
	 * @param groupId
	 * @param score此次增加分数
	 * @return
	 */
	public Integer updateUserScore(Long userId, Long groupId, Long score);
}
