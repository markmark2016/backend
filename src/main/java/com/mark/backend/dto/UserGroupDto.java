package com.mark.backend.dto;

public class UserGroupDto {
	private Long groupId;
	private String groupName;
	private String groupImg;
	private String groupDesc;
	private String userClass;
	private String userStatus;
	private String associationName;
	private String associationDesc;
	private String associationImg;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupImg() {
		return groupImg;
	}

	public void setGroupImg(String groupImg) {
		this.groupImg = groupImg;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
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

	public String getAssociationImg() {
		return associationImg;
	}

	public void setAssociationImg(String associationImg) {
		this.associationImg = associationImg;
	}

}
