package com.mark.backend.mysql.po;

import java.util.Date;

public class AssociationGroup {
    private Long id;

    private Long associationIdFk;

    private Long groupIdFk;

    private Date createTime;

    private Date updateTime;

    private String statius;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssociationIdFk() {
        return associationIdFk;
    }

    public void setAssociationIdFk(Long associationIdFk) {
        this.associationIdFk = associationIdFk;
    }

    public Long getGroupIdFk() {
        return groupIdFk;
    }

    public void setGroupIdFk(Long groupIdFk) {
        this.groupIdFk = groupIdFk;
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

    public String getStatius() {
        return statius;
    }

    public void setStatius(String statius) {
        this.statius = statius == null ? null : statius.trim();
    }
}