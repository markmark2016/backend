package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.GroupMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.mysql.po.GroupExample;
import com.mark.backend.mysql.po.GroupUser;
import com.mark.backend.mysql.po.GroupUserExample;
import com.mark.backend.service.IGroupService;
import com.mark.backend.utils.MarkUtils;
import com.mark.backend.vo.GroupVO;

@Service
public class GroupServiceImpl implements IGroupService {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(GroupServiceImpl.class);
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private GroupUserMapper groupUserMapper;

	@Override
	public List<GroupVO> getAllGroup() {
		GroupExample ex = new GroupExample();
		ex.createCriteria().andStatusNotEqualTo("0");
		List<Group> groupList = groupMapper.selectByExample(ex);
		List<GroupVO> voList = new ArrayList<GroupVO>();
		for (Group po : groupList) {
			GroupVO vo = new GroupVO();
			try {
				BeanUtils.copyProperties(po, vo);
			} catch (Exception e) {
				LOGGER.error("po转vo出错", e);
			}
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public GroupVO getGroupById(Long id) {
		Group po = groupMapper.selectByPrimaryKey(id);
		GroupVO vo = new GroupVO();
		try {
			BeanUtils.copyProperties(po, vo);
		} catch (Exception e) {
			LOGGER.error("po转vo出错", e);
		}
		return vo;
	}

	@Override
	public Long createGroup(GroupVO vo) {
		vo.setCreateTime(MarkUtils.getCurrentTime());
		vo.setUpdateTime(vo.getCreateTime());
		Group po = new Group();
		try {
			BeanUtils.copyProperties(vo, po);
		} catch (Exception e) {
			LOGGER.error("po转vo出错", e);
		}
		groupMapper.insert(po);
		Long groupId = po.getId();
		if (groupId > 0) {
			this.joinGroup(po.getId(), po.getUserIdFk(), "1");
		} else {
			LOGGER.warn("申请小组是发生错误");
		}
		return groupId;
	}

	@Override
	public Long joinGroup(Long groupId, Long userId, String clazz) {
		GroupUser po = new GroupUser();
		po.setGroupIdFk(groupId);
		po.setUserIdFk(userId);
		po.setCreateTime(MarkUtils.getCurrentTime());
		po.setUpdateTime(po.getCreateTime());
		po.setUserClass(clazz);
		po.setUserStatus("1");
		groupUserMapper.insert(po);
		Long groupUserId = po.getId();
		return groupUserId;
	}

	@Override
	public Integer quitGroup(Long groupId, Long userId) {
		GroupUser po = new GroupUser();
		po.setUserStatus("0");
		po.setUpdateTime(MarkUtils.getCurrentTime());
		GroupUserExample ex = new GroupUserExample();
		ex.createCriteria().andGroupIdFkEqualTo(groupId)
				.andUserIdFkEqualTo(userId);
		Integer updateFlag = groupUserMapper.updateByExampleSelective(po, ex);
		return updateFlag;
	}

	@Override
	public List<GroupVO> getUserGroup(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
