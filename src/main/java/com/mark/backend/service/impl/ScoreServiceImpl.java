package com.mark.backend.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.mapper.ScoreExMapper;
import com.mark.backend.mysql.mapper.ScoreMapper;
import com.mark.backend.mysql.po.Score;
import com.mark.backend.mysql.po.ScoreExample;
import com.mark.backend.service.IScoreService;

@Service
public class ScoreServiceImpl implements IScoreService {
	@Resource
	private ScoreExMapper sexMapper;
	@Resource
	private ScoreMapper scoreMapper;

	@Override
	public List<UserDto> getUserRankList() {
		List<UserDto> list = sexMapper.getAllScoreList();
		return list;
	}

	@Override
	public Integer updateUserScore(Long userId, Long groupId, Long scores) {
		ScoreExample ex = new ScoreExample();
		ex.createCriteria().andUserIdFkEqualTo(userId)
				.andGroupIdFkEqualTo(groupId);
		List<Score> list = scoreMapper.selectByExample(ex);
		int i = 0;
		if (list.size() > 0) {
			Score s = list.get(0);
			Long newScore = s.getScore() + scores;
			s.setScore(newScore);
			s.setUpdateTime(new Date());
			scoreMapper.updateByPrimaryKeySelective(s);
		} else {
			Score s = new Score();
			s.setGroupIdFk(groupId);
			s.setUserIdFk(userId);
			s.setCreateTime(new Date());
			s.setUpdateTime(new Date());
			s.setScore(scores);
			i = scoreMapper.insert(s);
		}
		return i;
	}

}
