package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.AssociationGroupMapper;
import com.mark.backend.mysql.mapper.AssociationMapper;
import com.mark.backend.mysql.mapper.GroupMapper;
import com.mark.backend.mysql.po.Association;
import com.mark.backend.mysql.po.AssociationExample;
import com.mark.backend.service.IAssociationService;
import com.mark.backend.vo.AssociationVO;

@Service
public class AssociationServiceImpl implements IAssociationService {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(AssociationServiceImpl.class);
	@Resource
	private AssociationMapper aMapper;
	@Resource
	private AssociationGroupMapper agMapper;
	@Resource
	private GroupMapper gMapper;

	@Override
	public List<AssociationVO> getAssociationList() {
		List<AssociationVO> avoList = new ArrayList<AssociationVO>();
		AssociationExample aex = new AssociationExample();
		aex.createCriteria().andStatusNotEqualTo("0");
		List<Association> apoList = aMapper.selectByExample(aex);
		for (Association apo : apoList) {
			AssociationVO avo = new AssociationVO();
			try {
				BeanUtils.copyProperties(apo, avo);
			} catch (Exception e) {
				LOGGER.error("po转vo出错");
			}
			avoList.add(avo);
		}
		return avoList;
	}

	@Override
	public AssociationVO getAssociationById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
