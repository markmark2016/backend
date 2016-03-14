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

	private Date beginTime;

	private Date endTime;

	private Integer guarantee;

	private String frequency;

	private Date latestTime;

	private String bookIdFk;

	private String readSlogan;

	private String groupDesc;

	private String captainBrief;

	private Long userIdFk;

	private String bookBrief;

	private String state;

	private String groupMode;

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
		this.frequency = frequency;
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
		this.bookIdFk = bookIdFk;
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

	public String getCaptainBrief() {
		return captainBrief;
	}

	public void setCaptainBrief(String captainBrief) {
		this.captainBrief = captainBrief;
	}

	public Long getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Long userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getBookBrief() {
		return bookBrief;
	}

	public void setBookBrief(String bookBrief) {
		this.bookBrief = bookBrief;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGroupMode() {
		return groupMode;
	}

	public void setGroupMode(String groupMode) {
		this.groupMode = groupMode;
	}

}
