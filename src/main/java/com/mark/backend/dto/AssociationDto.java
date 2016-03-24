package com.mark.backend.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssociationDto {
	private Long id;
	private String associationName;
	private String associationDesc;
	private String image;
	private List<GroupDto> groupList = new ArrayList<GroupDto>();
	// 类别map
	private Map<String, Object> categoryMap = new HashMap<String, Object>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public String getAssociationDesc() {
		return associationDesc;
	}

	public void setAssociationDesc(String associationDesc) {
		this.associationDesc = associationDesc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<GroupDto> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupDto> groupList) {
		this.groupList = groupList;
	}

	public Map<String, Object> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<String, Object> categoryMap) {
		this.categoryMap = categoryMap;
	}

}
