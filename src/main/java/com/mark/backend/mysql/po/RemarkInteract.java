package com.mark.backend.mysql.po;

import java.util.Date;

public class RemarkInteract {
    private Long id;

    private Long remarkIdFk;

    private Long interactIdFk;

    private String type;

    private Date createTime;

    private Date updateTime;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRemarkIdFk() {
        return remarkIdFk;
    }

    public void setRemarkIdFk(Long remarkIdFk) {
        this.remarkIdFk = remarkIdFk;
    }

    public Long getInteractIdFk() {
        return interactIdFk;
    }

    public void setInteractIdFk(Long interactIdFk) {
        this.interactIdFk = interactIdFk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}