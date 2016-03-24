package com.mark.backend.mysql.po;

import java.util.Date;

public class Group {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Date beginTime;

    private Date endTime;

    private String groupName;

    private Integer guarantee;

    private String frequency;

    private Date latestTime;

    private String bookIdFk;

    private String bookBrief;

    private String bookName;

    private String readSlogan;

    private String captainBrief;

    private Long userIdFk;

    private String captainName;

    private String captainEmail;

    private String captainWecode;

    private String captainPhone;

    private String groupMode;

    private String status;

    private String wechatQrcode;

    private Long categoryIdFk;

    private String remarkVisiable;

    private String listVisiable;

    private String groupDesc;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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

    public String getBookBrief() {
        return bookBrief;
    }

    public void setBookBrief(String bookBrief) {
        this.bookBrief = bookBrief == null ? null : bookBrief.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getReadSlogan() {
        return readSlogan;
    }

    public void setReadSlogan(String readSlogan) {
        this.readSlogan = readSlogan == null ? null : readSlogan.trim();
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

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName == null ? null : captainName.trim();
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
        this.captainWecode = captainWecode == null ? null : captainWecode.trim();
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

    public String getWechatQrcode() {
        return wechatQrcode;
    }

    public void setWechatQrcode(String wechatQrcode) {
        this.wechatQrcode = wechatQrcode == null ? null : wechatQrcode.trim();
    }

    public Long getCategoryIdFk() {
        return categoryIdFk;
    }

    public void setCategoryIdFk(Long categoryIdFk) {
        this.categoryIdFk = categoryIdFk;
    }

    public String getRemarkVisiable() {
        return remarkVisiable;
    }

    public void setRemarkVisiable(String remarkVisiable) {
        this.remarkVisiable = remarkVisiable == null ? null : remarkVisiable.trim();
    }

    public String getListVisiable() {
        return listVisiable;
    }

    public void setListVisiable(String listVisiable) {
        this.listVisiable = listVisiable == null ? null : listVisiable.trim();
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }
}