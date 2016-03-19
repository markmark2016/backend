package com.mark.backend.dto;

/**
 * 用户丰富传输对象
 * 
 * @Title:UserDto
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月16日
 * 
 */
public class UserDto {
	private Long id;
	private String nickName;
	private String headImage;
	private String intro;
	private String totalGroup;
	private Integer totalRemark;
	private Integer totalRead;
	private Integer punch;
	private boolean isLeader;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTotalGroup() {
		return totalGroup;
	}

	public void setTotalGroup(String totalGroup) {
		this.totalGroup = totalGroup;
	}

	public Integer getTotalRemark() {
		return totalRemark;
	}

	public void setTotalRemark(Integer totalRemark) {
		this.totalRemark = totalRemark;
	}

	public Integer getTotalRead() {
		return totalRead;
	}

	public void setTotalRead(Integer totalRead) {
		this.totalRead = totalRead;
	}

	public Integer getPunch() {
		return punch;
	}

	public void setPunch(Integer punch) {
		this.punch = punch;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

}
