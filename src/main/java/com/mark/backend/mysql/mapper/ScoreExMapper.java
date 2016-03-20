package com.mark.backend.mysql.mapper;

import java.util.List;

import com.mark.backend.dto.GroupDto;
import com.mark.backend.dto.UserDto;

public interface ScoreExMapper {
	/**
	 * 全站积分榜
	 * 
	 * @return
	 */
	public List<UserDto> getAllScoreList();

	/**
	 * 获得用户排名
	 * 
	 * @param id
	 * @return
	 */
	public Integer getUserRank(Long userId);

	/**
	 * 获得用户各个小组的积分
	 * 
	 * @param userId
	 * @return
	 */
	public List<GroupDto> getUserGroupScore(Long userId);
}
