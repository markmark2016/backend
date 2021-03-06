package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.mark.backend.mysql.mapper.BookMapper;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.GroupMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.po.AssociationGroup;
import com.mark.backend.mysql.po.AssociationGroupExample;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.mysql.po.GroupExample;
import com.mark.backend.mysql.po.GroupUser;
import com.mark.backend.mysql.po.GroupUserExample;
import com.mark.backend.mysql.po.User;
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
	@Resource
	private BookMapper bookMapper;
	@Resource
	private UserMapper userMapper;

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
			po.setStatus("0");
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
		GroupUserExample ex = new GroupUserExample();
		ex.createCriteria().andGroupIdFkEqualTo(groupId)
				.andUserIdFkEqualTo(userId);
		List<GroupUser> list = groupUserMapper.selectByExample(ex);
		if (list.size() > 0) {
			this.updateGroupUserStatus(groupId, userId, "1");
			return 1L;
		} else {
			po.setGroupIdFk(groupId);
			po.setUserIdFk(userId);
			po.setCreateTime(MarkUtils.getCurrentTime());
			po.setUpdateTime(po.getCreateTime());
			po.setUserClass(clazz);
			po.setUserStatus("1");
			groupUserMapper.insert(po);
			return 1L;
		}

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
		String isApprove = params.get("approve").toString();

		if (StringUtils.isEmpty(isApprove)) {
			Long bookId = Long.parseLong(group.getBookIdFk());
			// 设置小组对应的书籍名称
			Book book = bookMapper.selectByPrimaryKey(bookId);
			group.setBookName(book.getTitle());
		}

		// 设置小组的领读人名称
		User user = userMapper.selectByPrimaryKey(group.getUserIdFk());
		Long associationId = (Long) params.get("associationId");
		// Long categoryId = (Long) params.get("categoryId");

		Integer i = 0;
		// 先对小组信息做处理

		// 小组已存在，更新其信息
		if (group.getId() != null) {
			group.setUpdateTime(group.getUpdateTime());

			group.setCaptainName(user.getNickname());
			groupMapper.updateByPrimaryKeySelective(group);
			// 先把之前的领读人删除
			GroupUserExample ex = new GroupUserExample();
			ex.createCriteria().andGroupIdFkEqualTo(group.getId())
					.andUserClassEqualTo("1");
			groupUserMapper.deleteByExample(ex);

			GroupUser gu = new GroupUser();
			gu.setGroupIdFk(group.getId());
			gu.setUserIdFk(group.getUserIdFk());
			gu.setUserClass("1");
			gu.setUserStatus("1");
			gu.setCreateTime(new Date());
			gu.setUpdateTime(new Date());
			groupUserMapper.insert(gu);
		}
		// 此为新建小组
		else {
			if (!StringUtils.isEmpty(isApprove)) {
				group.setStatus("2");
			}
			// 更新小组表信息
			group.setCaptainName(user.getNickname());
			group.setCreateTime(MarkUtils.getCurrentTime());
			group.setUpdateTime(group.getCreateTime());
			groupMapper.insert(group);

			GroupUser gu = new GroupUser();
			gu.setGroupIdFk(group.getId());
			gu.setUserIdFk(group.getUserIdFk());
			gu.setUserClass("1");
			gu.setUserStatus("1");
			gu.setCreateTime(new Date());
			gu.setUpdateTime(new Date());
			groupUserMapper.insert(gu);
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

	@Override
	public Integer deleteGroupById(Long groupId) {
		int i = groupMapper.deleteByPrimaryKey(groupId);
		AssociationGroupExample ex = new AssociationGroupExample();
		ex.createCriteria().andGroupIdFkEqualTo(groupId);
		agMapper.deleteByExample(ex);
		return i;
	}

	@Override
	public Integer approveGroup(Map<String, Object> params) {
		Group group = (Group) params.get("group");
		groupMapper.updateByPrimaryKeySelective(group);
		return 1;
	}
}
