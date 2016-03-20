package com.mark.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class AssociationDto {
	private Long id;
	private String associationName;
	private String associationDesc;
	private String image;
	private List<GroupDto> groupList = new ArrayList<GroupDto>();

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

}
