package com.mark.backend.dto;

import java.util.Date;

public class RemarkDto {
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户头像地址
	 */
	private String headImage;
	/**
	 * 小组id
	 */
	private Long groupId;
	/**
	 * 书评id
	 */
	private Long remarkId;
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
	 * 是否完成阅读
	 */
	private Boolean isComplete;
	/**
	 * user完成阅读日期
	 */
	private Date readCompleteDate;
	/**
	 * 今日是否打卡
	 */
	private Boolean isPunch = false;
	/**
	 * 点赞数
	 */
	private Integer totalLike = 0;
	/**
	 * 回复数
	 */
	private Integer totalReply = 0;
	/**
	 * 发表日期
	 */
	private Date createTime;
	/**
	 * 评论标题
	 */
	private String title;
	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 上一次书评的结束页码
	 */
	private Integer lastPage;
	/**
	 * 是否点过赞
	 */
	private String hasLike;
	/**
	 * 小组状态
	 */
	private String groupStatus;

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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(Long remarkId) {
		this.remarkId = remarkId;
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

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
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

	public Integer getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(Integer totalLike) {
		this.totalLike = totalLike;
	}

	public Integer getTotalReply() {
		return totalReply;
	}

	public void setTotalReply(Integer totalReply) {
		this.totalReply = totalReply;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public String getHasLike() {
		return hasLike;
	}

	public void setHasLike(String hasLike) {
		this.hasLike = hasLike;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

}
