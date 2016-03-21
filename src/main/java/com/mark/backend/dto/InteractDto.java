package com.mark.backend.dto;

import java.util.Date;

public class InteractDto {
	/**
	 * 微信用户openId
	 */
	private String openid;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 头像地址
	 */
	private String headImage;
	/**
	 * 交互日期
	 */
	private Date interactDate;
	/**
	 * 评论内容
	 */
	private String content;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public Date getInteractDate() {
		return interactDate;
	}

	public void setInteractDate(Date interactDate) {
		this.interactDate = interactDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
