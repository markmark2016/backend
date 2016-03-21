package com.mark.backend.mysql.po;

import java.util.Date;

public class Remark {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long groupIdFk;

    private Long bookIdFk;

    private Long userIdFk;

    private Integer startPage;

    private Integer endPage;

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

    public Long getGroupIdFk() {
        return groupIdFk;
    }

    public void setGroupIdFk(Long groupIdFk) {
        this.groupIdFk = groupIdFk;
    }

    public Long getBookIdFk() {
        return bookIdFk;
    }

    public void setBookIdFk(Long bookIdFk) {
        this.bookIdFk = bookIdFk;
    }

    public Long getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(Long userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }
}