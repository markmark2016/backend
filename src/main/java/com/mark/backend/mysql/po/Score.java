package com.mark.backend.mysql.po;

import java.util.Date;

public class Score {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long score;

    private Long userIdFk;

    private Long groupIdFk;

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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(Long userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Long getGroupIdFk() {
        return groupIdFk;
    }

    public void setGroupIdFk(Long groupIdFk) {
        this.groupIdFk = groupIdFk;
    }
}