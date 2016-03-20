package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.dto.AssociationDto;
import com.mark.backend.dto.GroupDto;
import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.mapper.AssociationExMapper;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.UserExMapper;
import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserExample;
import com.mark.backend.service.IUserService;
import com.mark.backend.utils.MarkUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private UserExMapper uexMapper;
	@Resource
	private GroupExMapper gexMapper;
	@Resource
	private AssociationExMapper aexMapper;

	@Override
	public User getUserByOpenId(String openId) {
		UserExample ue = new UserExample();
		ue.createCriteria().andOpenidEqualTo(openId);
		User u = userMapper.selectByExample(ue).get(0);
		return u;
	}

	@Override
	public int insertUser(User user) {
		return userMapper.insert(user);
	}

	@Override
	public List<UserDto> queryUserList(Map<String, Object> params) {
		List<UserDto> dtoList = uexMapper.queryUserList(params);
		return dtoList;
	}

	@Override
	public List<User> getUserList() {
		UserExample ue = new UserExample();
		List<User> userList = userMapper.selectByExample(ue);
		return userList;
	}

	@Override
	public UserDto queryUserPageInfo(Map<String, Object> params) {
		UserDto dto = uexMapper.queryUserPageInfo(params);
		return dto;
	}

	@Override
	public Integer updateUserDetailInfo(String openId, User user) {
		UserExample ex = new UserExample();
		ex.createCriteria().andOpenidEqualTo(openId);
		user.setUpdateTime(MarkUtils.getCurrentTime());
		int i = userMapper.updateByExampleSelective(user, ex);
		return i;
	}

	@Override
	public Map<String, Object> getUserGroupDetail(Long userId) {
		List<GroupDto> gdtoList = gexMapper.getUserGroupList(userId);
		List<GroupDto> finalgdtoList = new ArrayList<GroupDto>();
		List<AssociationDto> assdtoList = new ArrayList<AssociationDto>();
		Map<String, Object> params = new HashMap<String, Object>();
		for (GroupDto dto : gdtoList) {
			if (dto.getAssociationId() != null) {
				params.put("associationId", dto.getAssociationId());
				AssociationDto adto = aexMapper.queryAssociationList(params)
						.get(0);
				assdtoList.add(adto);
			}
		}
		for (AssociationDto dto : assdtoList) {
			for (GroupDto groupDto : gdtoList) {
				if (groupDto.getAssociationId() == dto.getId()) {
					dto.getGroupList().add(groupDto);
				}
			}
		}
		for (GroupDto groupDto : gdtoList) {
			if (groupDto.getAssociationId() == null) {
				finalgdtoList.add(groupDto);
			}
		}
		params.clear();
		params.put("groupList", finalgdtoList);
		params.put("associationList", assdtoList);
		return params;
	}

	@Override
	public List<GroupDto> getUserReadedList(Long userId) {
		List<GroupDto> gdtoList = gexMapper.getUserGroupList(userId);
		List<GroupDto> finalgdtoList = new ArrayList<GroupDto>();
		for (GroupDto groupDto : gdtoList) {
			if ("2".equals(groupDto.getUserStatus())) {
				finalgdtoList.add(groupDto);
			}
		}
		return finalgdtoList;
	}
}
