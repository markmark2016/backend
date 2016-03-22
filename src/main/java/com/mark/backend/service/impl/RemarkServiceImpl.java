package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.dto.GroupDto;
import com.mark.backend.dto.InteractDto;
import com.mark.backend.dto.RemarkDto;
import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.mapper.InteractExMapper;
import com.mark.backend.mysql.mapper.RemarkExMapper;
import com.mark.backend.mysql.mapper.RemarkMapper;
import com.mark.backend.mysql.po.Remark;
import com.mark.backend.mysql.po.RemarkExample;
import com.mark.backend.mysql.po.RemarkWithBLOBs;
import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IRemarkService;
import com.mark.backend.utils.MarkUtils;

@Service
public class RemarkServiceImpl implements IRemarkService {

	@Resource
	private RemarkMapper remarkMapper;
	@Resource
	private RemarkExMapper rexMapper;
	@Resource
	private GroupExMapper gexMapper;
	@Resource
	private GroupUserMapper guMapper;
	@Resource
	private InteractExMapper iexMapper;

	@Override
	public List<RemarkDto> getPunchList(Long userId) {
		List<GroupDto> groupList = gexMapper.getUserGroupList(userId);
		List<RemarkDto> finalList = new ArrayList<RemarkDto>();
		for (GroupDto groupDto : groupList) {
			RemarkDto rdto = new RemarkDto();
			rdto.setGroupId(groupDto.getId());
			rdto.setGroupName(groupDto.getGroupName());
			rdto.setBookName(groupDto.getBookName());
			rdto.setImage(groupDto.getGroupImage());
			rdto.setUserId(userId);
			rdto.setReadCompleteDate(groupDto.getReadCompleteDate());
			rdto.setIsComplete("2".equals(groupDto.getUserStatus()));
			// 今日是否打卡了
			RemarkExample rex = new RemarkExample();
			rex.createCriteria().andGroupIdFkEqualTo(groupDto.getId())
					.andCreateTimeGreaterThan(MarkUtils.getZeroTime());
			List<Remark> remarkList = remarkMapper.selectByExample(rex);
			for (Remark remark : remarkList) {
				if (remark.getUserIdFk() == userId) {
					rdto.setIsPunch(true);
				}
			}
			// 今日该小组打卡人数
			rdto.setTodayPunch(remarkList.size());
			finalList.add(rdto);
		}
		return finalList;
	}

	@Override
	public Long createRemark(RemarkWithBLOBs remark) {
		remark.setCreateTime(MarkUtils.getCurrentTime());
		remark.setUpdateTime(remark.getCreateTime());
		remarkMapper.insert(remark);
		Long returnId = remark.getId();
		if (returnId > 0) {
			return remark.getId();
		} else {
			return -1L;
		}

	}

	@Override
	public Map<String, Object> getUserInGroupTodayRemark(Long userId,
			Long groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 用户信息
		User user = WeixinService.userMap.get(userId);
		// 主体信息
		RemarkExample rex = new RemarkExample();
		rex.createCriteria().andUserIdFkEqualTo(userId)
				.andGroupIdFkEqualTo(groupId)
				.andCreateTimeGreaterThan(MarkUtils.getZeroTime());
		RemarkWithBLOBs remark = remarkMapper.selectByExampleWithBLOBs(rex)
				.get(0);
		// 点赞列表
		List<UserDto> likeList = iexMapper.getLikeList(remark.getId());
		// 回复列表
		List<InteractDto> replyList = iexMapper.getReplyList(remark.getId());
		map.put("totalLike", likeList.size());
		map.put("likelist", likeList);
		map.put("replylist", replyList);
		map.put("user", user);
		map.put("remark", remark);
		return map;
	}

	@Override
	public Map<String, Object> getGroupRemark(Long groupId) {
		// 查询map，查点赞数和评论数用
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		// 热门的list
		List<RemarkDto> beginList = rexMapper.getGroupRemarkHotList(groupId);
		List<RemarkDto> finalList = new ArrayList<RemarkDto>();
		// 回复数id-count列表
		map.put("type", 1);
		List<RemarkDto> replyCountList = rexMapper
				.getGroupRemarkInteractList(map);
		// 点赞数id-count列表
		map.put("type", 2);
		List<RemarkDto> likeCountList = rexMapper
				.getGroupRemarkInteractList(map);
		for (RemarkDto hotDto : beginList) {
			User user = WeixinService.userMap.get(hotDto.getUserId());
			hotDto.setUserName(user.getNickname());
			hotDto.setHeadImage(user.getHeadImgUrl());
			for (RemarkDto replyDto : replyCountList) {
				if (replyDto.getRemarkId() == hotDto.getRemarkId()) {
					hotDto.setTotalReply(replyDto.getTotalReply());
				}
			}
			for (RemarkDto likeDto : likeCountList) {
				if (likeDto.getRemarkId() == hotDto.getRemarkId()) {
					hotDto.setTotalLike(likeDto.getTotalLike());
				}
			}
			finalList.add(hotDto);
		}
		map.clear();
		map.put("hotlist", finalList);
		return map;
	}
}
