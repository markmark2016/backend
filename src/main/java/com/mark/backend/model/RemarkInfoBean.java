package com.mark.backend.model;

import java.util.Date;

/**
 * @author liming
 */
public class RemarkInfoBean {
    private Long userId;
    private String userName;
    private String headImage;
    private Integer groupId;
    private Integer remarkId;
    private String groupName;
    private String bookName;
    private String image;
    private String author;
    private Integer todayPunch;
    private Integer continuePunch;
    private Integer groupTotalRemark;
    private Boolean isComplete;
    private Date readCompleteDate;
    private Boolean isPunch;
    private Integer totalLike;
    private Integer totalReply;
    private Date createTime;
    private String title;
    private String comment;
    private Integer lastPage;
    private Boolean hasLike;
    private Integer groupStatus;

    public RemarkInfoBean() {
    }

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(Integer remarkId) {
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

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Date getReadCompleteDate() {
        return readCompleteDate;
    }

    public void setReadCompleteDate(Date readCompleteDate) {
        this.readCompleteDate = readCompleteDate;
    }

    public Boolean getPunch() {
        return isPunch;
    }

    public void setPunch(Boolean punch) {
        isPunch = punch;
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

    public Boolean getHasLike() {
        return hasLike;
    }

    public void setHasLike(Boolean hasLike) {
        this.hasLike = hasLike;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }
}
