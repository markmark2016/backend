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

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

}
