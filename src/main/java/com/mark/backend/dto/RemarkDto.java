package com.mark.backend.dto;

import java.util.Date;

public class RemarkDto {
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 小组id
	 */
	private Long groupId;
	/**
	 * 书评id
	 */
	private Long reamarkId;
	/**
	 * 小组名
	 */
	private String groupName;
	/**
	 * 书名
	 */
	private String bookName;
	/**
	 * 小组图片
	 */
	private String image;
	/**
	 * 书作者
	 */
	private String author;
	/**
	 * 今日打卡人数
	 */
	private Integer todayPunch;
	/**
	 * 连续打卡天数
	 */
	private Integer continuePunch;
	/**
	 * user在改group的总书评数
	 */
	private Integer groupTotalRemark;
	/**
	 * user完成阅读日期
	 */
	private Date readCompleteDate;
	/**
	 * 今日是否打卡
	 */
	private Boolean isPunch = false;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getReamarkId() {
		return reamarkId;
	}

	public void setReamarkId(Long reamarkId) {
		this.reamarkId = reamarkId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getTodayPunch() {
		return todayPunch;
	}

	public void setTodayPunch(Integer todayPunch) {
		this.todayPunch = todayPunch;
	}

	public Integer getContinuePunch() {
		return continuePunch;
	}

	public void setContinuePunch(Integer continuePunch) {
		this.continuePunch = continuePunch;
	}

	public Integer getGroupTotalRemark() {
		return groupTotalRemark;
	}

	public void setGroupTotalRemark(Integer groupTotalRemark) {
		this.groupTotalRemark = groupTotalRemark;
	}

	public Date getReadCompleteDate() {
		return readCompleteDate;
	}

	public void setReadCompleteDate(Date readCompleteDate) {
		this.readCompleteDate = readCompleteDate;
	}

	public Boolean getIsPunch() {
		return isPunch;
	}

	public void setIsPunch(Boolean isPunch) {
		this.isPunch = isPunch;
	}

}
