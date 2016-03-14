package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.GroupMapper;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.mysql.po.GroupExample;
import com.mark.backend.service.IGroupService;
import com.mark.backend.vo.GroupVO;

@Service
public class GroupServiceImpl implements IGroupService {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(GroupServiceImpl.class);
	@Resource
	private GroupMapper groupMapper;

	@Override
	public List<GroupVO> getAllGroup() {
		GroupExample ex = new GroupExample();
		ex.createCriteria().andStatusEqualTo("1");
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
}
