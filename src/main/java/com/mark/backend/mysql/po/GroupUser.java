package com.mark.backend.mysql.po;

import java.util.Date;

public class GroupUser {
	private Long id;

	private Long groupIdFk;

	private Long userIdFk;

	private Date createTime;

	private Date updateTime;

	private String userStatus;

	private String userClass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupIdFk() {
		return groupIdFk;
	}

	public void setGroupIdFk(Long groupIdFk) {
		this.groupIdFk = groupIdFk;
	}

	public Long getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Long userIdFk) {
		this.userIdFk = userIdFk;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus == null ? null : userStatus.trim();
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass == null ? null : userClass.trim();
	}
}