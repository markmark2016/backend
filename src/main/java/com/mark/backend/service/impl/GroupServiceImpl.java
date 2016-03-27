package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mark.backend.dto.GroupDto;
import com.mark.backend.mysql.mapper.AssociationGroupMapper;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.GroupMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.po.AssociationGroup;
import com.mark.backend.mysql.po.AssociationGroupExample;
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
	@Resource
	private GroupExMapper groupExMapper;

	@Resource
	private AssociationGroupMapper agMapper;

	@Override
	public List<Group> getAllGroup(Map<String, Object> params) {
		String status = params.get("status").toString();
		Long categoryId = (Long) params.get("categoryId");
		GroupExample ex = new GroupExample();
		// 查看小组列表
		if ("group".equals(status)) {
			// 若有categoryid 查询这个类别下的
			if (categoryId != null) {
				ex.createCriteria().andStatusNotEqualTo("0")
						.andCategoryIdFkEqualTo(categoryId);
			} else {
				ex.createCriteria().andStatusNotEqualTo("0");
			}
		}
		// 查看申请小组列表
		else {
			ex.createCriteria().andStatusEqualTo("0");
		}
		List<Group> groupList = groupMapper.selectByExample(ex);
		return groupList;
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
		Integer updateFlag = this.updateGroupUserStatus(groupId, userId, "0");
		return updateFlag;
	}

	@Override
	public List<GroupDto> getGroupList(Map<String, Object> params) {
		List<GroupDto> resultList = groupExMapper.queryGroupList(params);
		// List<GroupDto> resultList = groupExMapper.getAllGroupList();
		return resultList;
	}

	@Override
	public List<GroupVO> getApplyGroup() {
		GroupExample ex = new GroupExample();
		ex.createCriteria().andStatusEqualTo("0");
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
	public List<GroupDto> getUserGroupList(Long userId) {
		List<GroupDto> userGroupList = groupExMapper.getUserGroupList(userId);
		return userGroupList;
	}

	@Override
	public Integer updateGroupUserStatus(Long groupId, Long userId,
			String status) {
		GroupUser po = new GroupUser();
		po.setUserStatus(status);
		po.setUpdateTime(MarkUtils.getCurrentTime());
		GroupUserExample ex = new GroupUserExample();
		ex.createCriteria().andGroupIdFkEqualTo(groupId)
				.andUserIdFkEqualTo(userId);
		Integer updateFlag = groupUserMapper.updateByExampleSelective(po, ex);
		return updateFlag;
	}

	@Override
	public Group getGroupInfo(Long groupId) {
		GroupExample ex = new GroupExample();
		ex.createCriteria().andIdEqualTo(groupId);
		Group group = groupMapper.selectByPrimaryKey(groupId);
		return group;
	}

	@Override
	public Integer saveGroup(Map<String, Object> params) {
		Group group = (Group) params.get("group");
		Long associationId = (Long) params.get("associationId");
		// Long categoryId = (Long) params.get("categoryId");
		String isApprove = params.get("approve").toString();
		Integer i = 0;
		//先对小组信息做处理
		if (group.getId() != null) {
			group.setUpdateTime(group.getUpdateTime());
			groupMapper.updateByPrimaryKeySelective(group);
		} else {
			if (!StringUtils.isEmpty(isApprove)) {
				group.setStatus("2");
			}
			group.setCreateTime(MarkUtils.getCurrentTime());
			group.setUpdateTime(group.getCreateTime());
			groupMapper.insert(group);
		}
		// 存储小组社群关系
		if (associationId != null) {
			// 先查数据库中是否已经有小组和社群关系
			AssociationGroup ag = new AssociationGroup();
			AssociationGroupExample agex = new AssociationGroupExample();
			agex.createCriteria().andGroupIdFkEqualTo(group.getId());
			List<AssociationGroup> agList = agMapper.selectByExample(agex);
			// 结果集若有，只有一个
			if (agList.size() > 0) {
				ag = agList.get(0);
				ag.setUpdateTime(MarkUtils.getCurrentTime());
				ag.setAssociationIdFk(associationId);
				agMapper.updateByPrimaryKeySelective(ag);
			}
			// 若无插入新纪录
			else {
				ag.setAssociationIdFk(associationId);
				ag.setGroupIdFk(group.getId());
				ag.setCreateTime(MarkUtils.getCurrentTime());
				ag.setUpdateTime(ag.getCreateTime());
				ag.setStatus("1");
				agMapper.insert(ag);
			}
		}
		return i;
	}
}
