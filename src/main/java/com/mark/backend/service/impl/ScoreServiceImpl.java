package com.mark.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.mapper.ScoreExMapper;
import com.mark.backend.service.IScoreService;

public class ScoreServiceImpl implements IScoreService {
	@Resource
	private ScoreExMapper sexMapper;

	@Override
	public List<UserDto> getUserRankList() {
		List<UserDto> list = sexMapper.getAllScoreList();
		return list;
	}

}
