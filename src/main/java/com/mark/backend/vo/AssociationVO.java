package com.mark.backend.vo;

import java.util.Date;
import java.util.List;

public class AssociationVO {
	private Long id;

	private Date createTime;

	private Date updateTime;

	private String name;

	private String status;

	private Long userIdFk;

	private String associationDesc;

	private String slogan;

	private List<GroupVO> groupList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Long getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Long userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getAssociationDesc() {
		return associationDesc;
	}

	public void setAssociationDesc(String associationDesc) {
		this.associationDesc = associationDesc == null ? null : associationDesc
				.trim();
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan == null ? null : slogan.trim();
	}

	public List<GroupVO> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupVO> groupList) {
		this.groupList = groupList;
	}
}
