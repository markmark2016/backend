package com.mark.backend.vo;

import java.util.Date;

/**
 * 小组VO
 * 
 * @Title:GroupVO
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月14日
 * 
 */
public class GroupVO {
	private Long id;

	private Date createTime;

	private Date updateTime;

	private String groupName;

	private Date beginTime;

	private Date endTime;

	private Integer guarantee;

	private String frequency;

	private Date latestTime;

	private String bookIdFk;

	private String bookName;

	private String bookBrief;

	private String readSlogan;

	private String groupDesc;

	private String captainBrief;

	private Long userIdFk;

	private String captainEmail;

	private String captainWecode;

	private String captainPhone;

	private String groupMode;

	private String status = "0";

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Integer guarantee) {
		this.guarantee = guarantee;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency == null ? null : frequency.trim();
	}

	public Date getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}

	public String getBookIdFk() {
		return bookIdFk;
	}

	public void setBookIdFk(String bookIdFk) {
		this.bookIdFk = bookIdFk == null ? null : bookIdFk.trim();
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName == null ? null : bookName.trim();
	}

	public String getBookBrief() {
		return bookBrief;
	}

	public void setBookBrief(String bookBrief) {
		this.bookBrief = bookBrief == null ? null : bookBrief.trim();
	}

	public String getReadSlogan() {
		return readSlogan;
	}

	public void setReadSlogan(String readSlogan) {
		this.readSlogan = readSlogan == null ? null : readSlogan.trim();
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc == null ? null : groupDesc.trim();
	}

	public String getCaptainBrief() {
		return captainBrief;
	}

	public void setCaptainBrief(String captainBrief) {
		this.captainBrief = captainBrief == null ? null : captainBrief.trim();
	}

	public Long getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Long userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getCaptainEmail() {
		return captainEmail;
	}

	public void setCaptainEmail(String captainEmail) {
		this.captainEmail = captainEmail == null ? null : captainEmail.trim();
	}

	public String getCaptainWecode() {
		return captainWecode;
	}

	public void setCaptainWecode(String captainWecode) {
		this.captainWecode = captainWecode == null ? null : captainWecode
				.trim();
	}

	public String getCaptainPhone() {
		return captainPhone;
	}

	public void setCaptainPhone(String captainPhone) {
		this.captainPhone = captainPhone == null ? null : captainPhone.trim();
	}

	public String getGroupMode() {
		return groupMode;
	}

	public void setGroupMode(String groupMode) {
		this.groupMode = groupMode == null ? null : groupMode.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

}
