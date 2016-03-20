package com.mark.backend.dto;

import java.util.Date;

/**
 * 小组显示相关
 * 
 * @Title:GroupDto
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月15日
 * 
 */
public class GroupDto {
	private Long id;
	/**
	 * 小组图片=图书图片
	 */
	private String groupImage;
	/**
	 * 小组名
	 */
	private String groupName;
	/**
	 * 所读书名
	 */
	private String bookName;
	/**
	 * 书籍作者名
	 */
	private String author;
	/**
	 * 读书标语
	 */
	private String readSlogan;

	/**
	 * 小组简介
	 */
	private String groupDesc;
	/**
	 * 开始时间
	 */
	private Date beginDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 频率
	 */
	private String frequency;
	/**
	 * 保证金
	 */
	private String guarantee;
	/**
	 * 最晚加入时间
	 */
	private Date latestDate;
	/**
	 * 小组所属社群id
	 */
	private Long associationId;
	/**
	 * 用户在小组的等级
	 */
	private String userClass;
	/**
	 * 用户咋小组的状态
	 */
	private String userStatus;
	/**
	 * 用户在该小组的积分
	 */
	private Integer score;

	/**
	 * 图书简介
	 */
	private String bookSummary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReadSlogan() {
		return readSlogan;
	}

	public void setReadSlogan(String readSlogan) {
		this.readSlogan = readSlogan;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	public Long getAssociationId() {
		return associationId;
	}

	public void setAssociationId(Long associationId) {
		this.associationId = associationId;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getBookSummary() {
		return bookSummary;
	}

	public void setBookSummary(String bookSummary) {
		this.bookSummary = bookSummary;
	}

}
