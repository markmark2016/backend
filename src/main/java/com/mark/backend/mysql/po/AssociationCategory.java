package com.mark.backend.mysql.po;

import java.util.Date;

public class AssociationCategory {
    private Long id;

    private Date createTime;

    private Long associationIdFk;

    private Long categoryIdFk;

    private String status;

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

    public Long getAssociationIdFk() {
        return associationIdFk;
    }

    public void setAssociationIdFk(Long associationIdFk) {
        this.associationIdFk = associationIdFk;
    }

    public Long getCategoryIdFk() {
        return categoryIdFk;
    }

    public void setCategoryIdFk(Long categoryIdFk) {
        this.categoryIdFk = categoryIdFk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}