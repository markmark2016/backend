package com.mark.backend.dto;

import java.util.Date;

public class MessageDto {
	/**
	 * 发出此信息的用户id
	 */
	private Long userId;
	/**
	 * 用户昵称
	 */
	private String userName;
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 书评id外键
	 */
	private Long remarkId;
	/**
	 * url地址
	 */
	private String url;
	/**
	 * 发生时间
	 */
	private Date createTime;
	/**
	 * 是否已读
	 */
	private String isCheck;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(Long remarkId) {
		this.remarkId = remarkId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

}
