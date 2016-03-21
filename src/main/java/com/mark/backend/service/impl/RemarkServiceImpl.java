package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.dto.GroupDto;
import com.mark.backend.dto.RemarkDto;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.mapper.RemarkExMapper;
import com.mark.backend.mysql.mapper.RemarkMapper;
import com.mark.backend.mysql.po.Remark;
import com.mark.backend.mysql.po.RemarkExample;
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
	public Long createRemark(Remark remark) {
		remark.setCreateTime(MarkUtils.getCurrentTime());
		remark.setUpdateTime(remark.getCreateTime());
		Integer returnId = remarkMapper.insert(remark);
		if (returnId > 0) {
			return remark.getId();
		} else {
			return -1L;
		}

	}
}
