package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mark.backend.dto.AssociationDto;
import com.mark.backend.dto.GroupDto;
import com.mark.backend.mysql.mapper.AssociationCategoryMapper;
import com.mark.backend.mysql.mapper.AssociationExMapper;
import com.mark.backend.mysql.mapper.AssociationMapper;
import com.mark.backend.mysql.mapper.CategoryMapper;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.PictureMapper;
import com.mark.backend.mysql.po.Association;
import com.mark.backend.mysql.po.AssociationCategory;
import com.mark.backend.mysql.po.AssociationCategoryExample;
import com.mark.backend.mysql.po.AssociationExample;
import com.mark.backend.mysql.po.Category;
import com.mark.backend.mysql.po.CategoryExample;
import com.mark.backend.mysql.po.Picture;
import com.mark.backend.mysql.po.PictureExample;
import com.mark.backend.service.IAssociationService;
import com.mark.backend.utils.MarkUtils;

@Service
public class AssociationServiceImpl implements IAssociationService {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(AssociationServiceImpl.class);

	@Resource
	private AssociationExMapper aexMapper;
	@Resource
	private GroupExMapper gexMapper;
	@Resource
	private CategoryMapper categoryMapper;
	@Resource
	private AssociationCategoryMapper acMapper;
	@Resource
	private AssociationMapper associationMapper;
	@Resource
	private PictureMapper picMapper;

	@Override
	public List<AssociationDto> getAssociationList(Map<String, Object> params) {
		List<AssociationDto> resultList = aexMapper
				.queryAssociationList(params);
		return resultList;

	}

	@Override
	public AssociationDto getAssociationById(Map<String, Object> params) {
		Long aId = (Long) params.get("associationId");
		List<AssociationDto> resultList = aexMapper
				.queryAssociationList(params);
		AssociationDto dto = new AssociationDto();
		if (resultList.size() > 0) {
			dto = resultList.get(0);
			List<GroupDto> groupList = gexMapper.queryGroupList(params);
			List<Category> categoryList = getCategoryList(aId);
			// 将小组分到所属的类别
			// 循环类别
			for (Category category : categoryList) {
				// Map<String, Object> categoryMap = new HashMap<String,
				// Object>();
				List<GroupDto> clist = new ArrayList<GroupDto>();
				// 循环小组
				for (GroupDto group : groupList) {
					if (category.getId() == group.getCategoryId()) {
						clist.add(group);
					}
					// categoryMap.put("categoryname",
					// category.getCategoryName());
					// categoryMap.put("groupList", clist);
				}
				// 把这个小组名放到map
				dto.getCategoryMap().put(category.getCategoryName(), clist);
			}
			// 无类别是使用
			// dto.setGroupList(groupList);
		} else {
			LOGGER.error("所查询的小组不存在,id:" + params.get("associationId"));
		}
		return dto;
	}

	/**
	 * 获得社群的类别 id
	 * 
	 * @param aId
	 * @return
	 */
	public List<Long> categroyIdList(Long aId) {
		AssociationCategoryExample ex = new AssociationCategoryExample();
		ex.createCriteria().andAssociationIdFkEqualTo(aId)
				.andStatusEqualTo("1");
		List<AssociationCategory> list = acMapper.selectByExample(ex);
		List<Long> idList = new ArrayList<Long>();
		for (AssociationCategory ac : list) {
			idList.add(ac.getCategoryIdFk());
		}
		return idList;
	}

	/**
	 * 获得类别列表
	 * 
	 * @param idList
	 * @return
	 */
	public List<Category> getCategoryList(Long aId) {
		List<Long> idList = categroyIdList(aId);
		List<Category> list = new ArrayList<Category>();
		if (idList.size() > 0) {
			CategoryExample ex = new CategoryExample();
			ex.createCriteria().andIdIn(idList);
			list = categoryMapper.selectByExample(ex);
		}
		return list;
	}

	@Override
	public List<Association> getAllList(Map<String, Object> params) {
		AssociationExample ex = new AssociationExample();
		ex.createCriteria();
		List<Association> associationList = associationMapper
				.selectByExample(ex);
		return associationList;
	}

	@Override
	public Map<String, Object> getAssociationById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Association ass = associationMapper.selectByPrimaryKey(id);
		PictureExample ex = new PictureExample();
		ex.createCriteria().andIdFkEqualTo(id).andTypeEqualTo("3");
		Picture picture = picMapper.selectByExample(ex).get(0);
		map.put("association", ass);
		map.put("pic", picture.getUrl());
		return map;
	}

	@Override
	public Integer editAssociation(Association association, String pictureUrl) {
		Long id = association.getId();
		Integer i = 0;
		if (id != null) {
			i = associationMapper.updateByPrimaryKeySelective(association);
			// 更新图片表
			PictureExample ex = new PictureExample();
			ex.createCriteria().andIdFkEqualTo(association.getId())
					.andTypeEqualTo("3");
			Picture picture = new Picture();
			picture.setUpdateTime(MarkUtils.getCurrentTime());
			picture.setUrl(pictureUrl);
			picMapper.updateByExampleSelective(picture, ex);
		} else {
			association.setCreateTime(MarkUtils.getCurrentTime());
			association.setUpdateTime(association.getCreateTime());
			association.setStatus("1");
			i = associationMapper.insert(association);
			// 插入图片表
			Picture picture = new Picture();
			picture.setCreateTime(MarkUtils.getCurrentTime());
			picture.setUpdateTime(picture.getCreateTime());
			picture.setIdFk(association.getId());
			picture.setType("3");
			picture.setUrl(pictureUrl);
			picMapper.insert(picture);
		}
		return i;
	}

	@Override
	public Integer deleteByAssociationId(Long associationId) {
		int i = associationMapper.deleteByPrimaryKey(associationId);
		return i;
	}

}
