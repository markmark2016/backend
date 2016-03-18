package com.mark.backend.mysql.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andPicIdFkIsNull() {
            addCriterion("pic_id_fk is null");
            return (Criteria) this;
        }

        public Criteria andPicIdFkIsNotNull() {
            addCriterion("pic_id_fk is not null");
            return (Criteria) this;
        }

        public Criteria andPicIdFkEqualTo(String value) {
            addCriterion("pic_id_fk =", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkNotEqualTo(String value) {
            addCriterion("pic_id_fk <>", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkGreaterThan(String value) {
            addCriterion("pic_id_fk >", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkGreaterThanOrEqualTo(String value) {
            addCriterion("pic_id_fk >=", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkLessThan(String value) {
            addCriterion("pic_id_fk <", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkLessThanOrEqualTo(String value) {
            addCriterion("pic_id_fk <=", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkLike(String value) {
            addCriterion("pic_id_fk like", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkNotLike(String value) {
            addCriterion("pic_id_fk not like", value, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkIn(List<String> values) {
            addCriterion("pic_id_fk in", values, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkNotIn(List<String> values) {
            addCriterion("pic_id_fk not in", values, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkBetween(String value1, String value2) {
            addCriterion("pic_id_fk between", value1, value2, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andPicIdFkNotBetween(String value1, String value2) {
            addCriterion("pic_id_fk not between", value1, value2, "picIdFk");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOriginTitleIsNull() {
            addCriterion("origin_title is null");
            return (Criteria) this;
        }

        public Criteria andOriginTitleIsNotNull() {
            addCriterion("origin_title is not null");
            return (Criteria) this;
        }

        public Criteria andOriginTitleEqualTo(String value) {
            addCriterion("origin_title =", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleNotEqualTo(String value) {
            addCriterion("origin_title <>", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleGreaterThan(String value) {
            addCriterion("origin_title >", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleGreaterThanOrEqualTo(String value) {
            addCriterion("origin_title >=", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleLessThan(String value) {
            addCriterion("origin_title <", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleLessThanOrEqualTo(String value) {
            addCriterion("origin_title <=", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleLike(String value) {
            addCriterion("origin_title like", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleNotLike(String value) {
            addCriterion("origin_title not like", value, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleIn(List<String> values) {
            addCriterion("origin_title in", values, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleNotIn(List<String> values) {
            addCriterion("origin_title not in", values, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleBetween(String value1, String value2) {
            addCriterion("origin_title between", value1, value2, "originTitle");
            return (Criteria) this;
        }

        public Criteria andOriginTitleNotBetween(String value1, String value2) {
            addCriterion("origin_title not between", value1, value2, "originTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleIsNull() {
            addCriterion("alt_title is null");
            return (Criteria) this;
        }

        public Criteria andAltTitleIsNotNull() {
            addCriterion("alt_title is not null");
            return (Criteria) this;
        }

        public Criteria andAltTitleEqualTo(String value) {
            addCriterion("alt_title =", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleNotEqualTo(String value) {
            addCriterion("alt_title <>", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleGreaterThan(String value) {
            addCriterion("alt_title >", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleGreaterThanOrEqualTo(String value) {
            addCriterion("alt_title >=", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleLessThan(String value) {
            addCriterion("alt_title <", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleLessThanOrEqualTo(String value) {
            addCriterion("alt_title <=", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleLike(String value) {
            addCriterion("alt_title like", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleNotLike(String value) {
            addCriterion("alt_title not like", value, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleIn(List<String> values) {
            addCriterion("alt_title in", values, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleNotIn(List<String> values) {
            addCriterion("alt_title not in", values, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleBetween(String value1, String value2) {
            addCriterion("alt_title between", value1, value2, "altTitle");
            return (Criteria) this;
        }

        public Criteria andAltTitleNotBetween(String value1, String value2) {
            addCriterion("alt_title not between", value1, value2, "altTitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleIsNull() {
            addCriterion("subtitle is null");
            return (Criteria) this;
        }

        public Criteria andSubtitleIsNotNull() {
            addCriterion("subtitle is not null");
            return (Criteria) this;
        }

        public Criteria andSubtitleEqualTo(String value) {
            addCriterion("subtitle =", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotEqualTo(String value) {
            addCriterion("subtitle <>", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleGreaterThan(String value) {
            addCriterion("subtitle >", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleGreaterThanOrEqualTo(String value) {
            addCriterion("subtitle >=", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLessThan(String value) {
            addCriterion("subtitle <", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLessThanOrEqualTo(String value) {
            addCriterion("subtitle <=", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleLike(String value) {
            addCriterion("subtitle like", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotLike(String value) {
            addCriterion("subtitle not like", value, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleIn(List<String> values) {
            addCriterion("subtitle in", values, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotIn(List<String> values) {
            addCriterion("subtitle not in", values, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleBetween(String value1, String value2) {
            addCriterion("subtitle between", value1, value2, "subtitle");
            return (Criteria) this;
        }

        public Criteria andSubtitleNotBetween(String value1, String value2) {
            addCriterion("subtitle not between", value1, value2, "subtitle");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andAltIsNull() {
            addCriterion("alt is null");
            return (Criteria) this;
        }

        public Criteria andAltIsNotNull() {
            addCriterion("alt is not null");
            return (Criteria) this;
        }

        public Criteria andAltEqualTo(String value) {
            addCriterion("alt =", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotEqualTo(String value) {
            addCriterion("alt <>", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltGreaterThan(String value) {
            addCriterion("alt >", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltGreaterThanOrEqualTo(String value) {
            addCriterion("alt >=", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltLessThan(String value) {
            addCriterion("alt <", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltLessThanOrEqualTo(String value) {
            addCriterion("alt <=", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltLike(String value) {
            addCriterion("alt like", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotLike(String value) {
            addCriterion("alt not like", value, "alt");
            return (Criteria) this;
        }

        public Criteria andAltIn(List<String> values) {
            addCriterion("alt in", values, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotIn(List<String> values) {
            addCriterion("alt not in", values, "alt");
            return (Criteria) this;
        }

        public Criteria andAltBetween(String value1, String value2) {
            addCriterion("alt between", value1, value2, "alt");
            return (Criteria) this;
        }

        public Criteria andAltNotBetween(String value1, String value2) {
            addCriterion("alt not between", value1, value2, "alt");
            return (Criteria) this;
        }

        public Criteria andTranslatorIsNull() {
            addCriterion("translator is null");
            return (Criteria) this;
        }

        public Criteria andTranslatorIsNotNull() {
            addCriterion("translator is not null");
            return (Criteria) this;
        }

        public Criteria andTranslatorEqualTo(String value) {
            addCriterion("translator =", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorNotEqualTo(String value) {
            addCriterion("translator <>", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorGreaterThan(String value) {
            addCriterion("translator >", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorGreaterThanOrEqualTo(String value) {
            addCriterion("translator >=", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorLessThan(String value) {
            addCriterion("translator <", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorLessThanOrEqualTo(String value) {
            addCriterion("translator <=", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorLike(String value) {
            addCriterion("translator like", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorNotLike(String value) {
            addCriterion("translator not like", value, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorIn(List<String> values) {
            addCriterion("translator in", values, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorNotIn(List<String> values) {
            addCriterion("translator not in", values, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorBetween(String value1, String value2) {
            addCriterion("translator between", value1, value2, "translator");
            return (Criteria) this;
        }

        public Criteria andTranslatorNotBetween(String value1, String value2) {
            addCriterion("translator not between", value1, value2, "translator");
            return (Criteria) this;
        }

        public Criteria andPubliserIsNull() {
            addCriterion("publiser is null");
            return (Criteria) this;
        }

        public Criteria andPubliserIsNotNull() {
            addCriterion("publiser is not null");
            return (Criteria) this;
        }

        public Criteria andPubliserEqualTo(String value) {
            addCriterion("publiser =", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserNotEqualTo(String value) {
            addCriterion("publiser <>", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserGreaterThan(String value) {
            addCriterion("publiser >", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserGreaterThanOrEqualTo(String value) {
            addCriterion("publiser >=", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserLessThan(String value) {
            addCriterion("publiser <", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserLessThanOrEqualTo(String value) {
            addCriterion("publiser <=", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserLike(String value) {
            addCriterion("publiser like", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserNotLike(String value) {
            addCriterion("publiser not like", value, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserIn(List<String> values) {
            addCriterion("publiser in", values, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserNotIn(List<String> values) {
            addCriterion("publiser not in", values, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserBetween(String value1, String value2) {
            addCriterion("publiser between", value1, value2, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubliserNotBetween(String value1, String value2) {
            addCriterion("publiser not between", value1, value2, "publiser");
            return (Criteria) this;
        }

        public Criteria andPubdateIsNull() {
            addCriterion("pubdate is null");
            return (Criteria) this;
        }

        public Criteria andPubdateIsNotNull() {
            addCriterion("pubdate is not null");
            return (Criteria) this;
        }

        public Criteria andPubdateEqualTo(String value) {
            addCriterion("pubdate =", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotEqualTo(String value) {
            addCriterion("pubdate <>", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateGreaterThan(String value) {
            addCriterion("pubdate >", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateGreaterThanOrEqualTo(String value) {
            addCriterion("pubdate >=", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLessThan(String value) {
            addCriterion("pubdate <", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLessThanOrEqualTo(String value) {
            addCriterion("pubdate <=", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLike(String value) {
            addCriterion("pubdate like", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotLike(String value) {
            addCriterion("pubdate not like", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateIn(List<String> values) {
            addCriterion("pubdate in", values, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotIn(List<String> values) {
            addCriterion("pubdate not in", values, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateBetween(String value1, String value2) {
            addCriterion("pubdate between", value1, value2, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotBetween(String value1, String value2) {
            addCriterion("pubdate not between", value1, value2, "pubdate");
            return (Criteria) this;
        }

        public Criteria andRatingMaxIsNull() {
            addCriterion("rating_max is null");
            return (Criteria) this;
        }

        public Criteria andRatingMaxIsNotNull() {
            addCriterion("rating_max is not null");
            return (Criteria) this;
        }

        public Criteria andRatingMaxEqualTo(String value) {
            addCriterion("rating_max =", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxNotEqualTo(String value) {
            addCriterion("rating_max <>", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxGreaterThan(String value) {
            addCriterion("rating_max >", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxGreaterThanOrEqualTo(String value) {
            addCriterion("rating_max >=", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxLessThan(String value) {
            addCriterion("rating_max <", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxLessThanOrEqualTo(String value) {
            addCriterion("rating_max <=", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxLike(String value) {
            addCriterion("rating_max like", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxNotLike(String value) {
            addCriterion("rating_max not like", value, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxIn(List<String> values) {
            addCriterion("rating_max in", values, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxNotIn(List<String> values) {
            addCriterion("rating_max not in", values, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxBetween(String value1, String value2) {
            addCriterion("rating_max between", value1, value2, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingMaxNotBetween(String value1, String value2) {
            addCriterion("rating_max not between", value1, value2, "ratingMax");
            return (Criteria) this;
        }

        public Criteria andRatingAverageIsNull() {
            addCriterion("rating_average is null");
            return (Criteria) this;
        }

        public Criteria andRatingAverageIsNotNull() {
            addCriterion("rating_average is not null");
            return (Criteria) this;
        }

        public Criteria andRatingAverageEqualTo(String value) {
            addCriterion("rating_average =", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageNotEqualTo(String value) {
            addCriterion("rating_average <>", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageGreaterThan(String value) {
            addCriterion("rating_average >", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageGreaterThanOrEqualTo(String value) {
            addCriterion("rating_average >=", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageLessThan(String value) {
            addCriterion("rating_average <", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageLessThanOrEqualTo(String value) {
            addCriterion("rating_average <=", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageLike(String value) {
            addCriterion("rating_average like", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageNotLike(String value) {
            addCriterion("rating_average not like", value, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageIn(List<String> values) {
            addCriterion("rating_average in", values, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageNotIn(List<String> values) {
            addCriterion("rating_average not in", values, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageBetween(String value1, String value2) {
            addCriterion("rating_average between", value1, value2, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingAverageNotBetween(String value1, String value2) {
            addCriterion("rating_average not between", value1, value2, "ratingAverage");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersIsNull() {
            addCriterion("rating_numRaters is null");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersIsNotNull() {
            addCriterion("rating_numRaters is not null");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersEqualTo(String value) {
            addCriterion("rating_numRaters =", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersNotEqualTo(String value) {
            addCriterion("rating_numRaters <>", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersGreaterThan(String value) {
            addCriterion("rating_numRaters >", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersGreaterThanOrEqualTo(String value) {
            addCriterion("rating_numRaters >=", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersLessThan(String value) {
            addCriterion("rating_numRaters <", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersLessThanOrEqualTo(String value) {
            addCriterion("rating_numRaters <=", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersLike(String value) {
            addCriterion("rating_numRaters like", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersNotLike(String value) {
            addCriterion("rating_numRaters not like", value, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersIn(List<String> values) {
            addCriterion("rating_numRaters in", values, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersNotIn(List<String> values) {
            addCriterion("rating_numRaters not in", values, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersBetween(String value1, String value2) {
            addCriterion("rating_numRaters between", value1, value2, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingNumratersNotBetween(String value1, String value2) {
            addCriterion("rating_numRaters not between", value1, value2, "ratingNumraters");
            return (Criteria) this;
        }

        public Criteria andRatingMinIsNull() {
            addCriterion("rating_min is null");
            return (Criteria) this;
        }

        public Criteria andRatingMinIsNotNull() {
            addCriterion("rating_min is not null");
            return (Criteria) this;
        }

        public Criteria andRatingMinEqualTo(String value) {
            addCriterion("rating_min =", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinNotEqualTo(String value) {
            addCriterion("rating_min <>", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinGreaterThan(String value) {
            addCriterion("rating_min >", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinGreaterThanOrEqualTo(String value) {
            addCriterion("rating_min >=", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinLessThan(String value) {
            addCriterion("rating_min <", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinLessThanOrEqualTo(String value) {
            addCriterion("rating_min <=", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinLike(String value) {
            addCriterion("rating_min like", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinNotLike(String value) {
            addCriterion("rating_min not like", value, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinIn(List<String> values) {
            addCriterion("rating_min in", values, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinNotIn(List<String> values) {
            addCriterion("rating_min not in", values, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinBetween(String value1, String value2) {
            addCriterion("rating_min between", value1, value2, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andRatingMinNotBetween(String value1, String value2) {
            addCriterion("rating_min not between", value1, value2, "ratingMin");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andBindingIsNull() {
            addCriterion("binding is null");
            return (Criteria) this;
        }

        public Criteria andBindingIsNotNull() {
            addCriterion("binding is not null");
            return (Criteria) this;
        }

        public Criteria andBindingEqualTo(String value) {
            addCriterion("binding =", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotEqualTo(String value) {
            addCriterion("binding <>", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingGreaterThan(String value) {
            addCriterion("binding >", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingGreaterThanOrEqualTo(String value) {
            addCriterion("binding >=", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLessThan(String value) {
            addCriterion("binding <", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLessThanOrEqualTo(String value) {
            addCriterion("binding <=", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLike(String value) {
            addCriterion("binding like", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotLike(String value) {
            addCriterion("binding not like", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingIn(List<String> values) {
            addCriterion("binding in", values, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotIn(List<String> values) {
            addCriterion("binding not in", values, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingBetween(String value1, String value2) {
            addCriterion("binding between", value1, value2, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotBetween(String value1, String value2) {
            addCriterion("binding not between", value1, value2, "binding");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(String value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(String value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(String value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(String value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(String value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(String value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(String value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<String> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<String> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(String value1, String value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(String value1, String value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andSeriesIsNull() {
            addCriterion("series is null");
            return (Criteria) this;
        }

        public Criteria andSeriesIsNotNull() {
            addCriterion("series is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesEqualTo(String value) {
            addCriterion("series =", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotEqualTo(String value) {
            addCriterion("series <>", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesGreaterThan(String value) {
            addCriterion("series >", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesGreaterThanOrEqualTo(String value) {
            addCriterion("series >=", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesLessThan(String value) {
            addCriterion("series <", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesLessThanOrEqualTo(String value) {
            addCriterion("series <=", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesLike(String value) {
            addCriterion("series like", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotLike(String value) {
            addCriterion("series not like", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesIn(List<String> values) {
            addCriterion("series in", values, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotIn(List<String> values) {
            addCriterion("series not in", values, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesBetween(String value1, String value2) {
            addCriterion("series between", value1, value2, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotBetween(String value1, String value2) {
            addCriterion("series not between", value1, value2, "series");
            return (Criteria) this;
        }

        public Criteria andPagesIsNull() {
            addCriterion("pages is null");
            return (Criteria) this;
        }

        public Criteria andPagesIsNotNull() {
            addCriterion("pages is not null");
            return (Criteria) this;
        }

        public Criteria andPagesEqualTo(String value) {
            addCriterion("pages =", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesNotEqualTo(String value) {
            addCriterion("pages <>", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesGreaterThan(String value) {
            addCriterion("pages >", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesGreaterThanOrEqualTo(String value) {
            addCriterion("pages >=", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesLessThan(String value) {
            addCriterion("pages <", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesLessThanOrEqualTo(String value) {
            addCriterion("pages <=", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesLike(String value) {
            addCriterion("pages like", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesNotLike(String value) {
            addCriterion("pages not like", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesIn(List<String> values) {
            addCriterion("pages in", values, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesNotIn(List<String> values) {
            addCriterion("pages not in", values, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesBetween(String value1, String value2) {
            addCriterion("pages between", value1, value2, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesNotBetween(String value1, String value2) {
            addCriterion("pages not between", value1, value2, "pages");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroIsNull() {
            addCriterion("author_intro is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroIsNotNull() {
            addCriterion("author_intro is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroEqualTo(String value) {
            addCriterion("author_intro =", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroNotEqualTo(String value) {
            addCriterion("author_intro <>", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroGreaterThan(String value) {
            addCriterion("author_intro >", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroGreaterThanOrEqualTo(String value) {
            addCriterion("author_intro >=", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroLessThan(String value) {
            addCriterion("author_intro <", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroLessThanOrEqualTo(String value) {
            addCriterion("author_intro <=", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroLike(String value) {
            addCriterion("author_intro like", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroNotLike(String value) {
            addCriterion("author_intro not like", value, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroIn(List<String> values) {
            addCriterion("author_intro in", values, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroNotIn(List<String> values) {
            addCriterion("author_intro not in", values, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroBetween(String value1, String value2) {
            addCriterion("author_intro between", value1, value2, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroNotBetween(String value1, String value2) {
            addCriterion("author_intro not between", value1, value2, "authorIntro");
            return (Criteria) this;
        }

        public Criteria andCatalogIsNull() {
            addCriterion("catalog is null");
            return (Criteria) this;
        }

        public Criteria andCatalogIsNotNull() {
            addCriterion("catalog is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogEqualTo(String value) {
            addCriterion("catalog =", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotEqualTo(String value) {
            addCriterion("catalog <>", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogGreaterThan(String value) {
            addCriterion("catalog >", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("catalog >=", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogLessThan(String value) {
            addCriterion("catalog <", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogLessThanOrEqualTo(String value) {
            addCriterion("catalog <=", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogLike(String value) {
            addCriterion("catalog like", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotLike(String value) {
            addCriterion("catalog not like", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogIn(List<String> values) {
            addCriterion("catalog in", values, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotIn(List<String> values) {
            addCriterion("catalog not in", values, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogBetween(String value1, String value2) {
            addCriterion("catalog between", value1, value2, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotBetween(String value1, String value2) {
            addCriterion("catalog not between", value1, value2, "catalog");
            return (Criteria) this;
        }

        public Criteria andEbookUrlIsNull() {
            addCriterion("ebook_url is null");
            return (Criteria) this;
        }

        public Criteria andEbookUrlIsNotNull() {
            addCriterion("ebook_url is not null");
            return (Criteria) this;
        }

        public Criteria andEbookUrlEqualTo(String value) {
            addCriterion("ebook_url =", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlNotEqualTo(String value) {
            addCriterion("ebook_url <>", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlGreaterThan(String value) {
            addCriterion("ebook_url >", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ebook_url >=", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlLessThan(String value) {
            addCriterion("ebook_url <", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlLessThanOrEqualTo(String value) {
            addCriterion("ebook_url <=", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlLike(String value) {
            addCriterion("ebook_url like", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlNotLike(String value) {
            addCriterion("ebook_url not like", value, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlIn(List<String> values) {
            addCriterion("ebook_url in", values, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlNotIn(List<String> values) {
            addCriterion("ebook_url not in", values, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlBetween(String value1, String value2) {
            addCriterion("ebook_url between", value1, value2, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookUrlNotBetween(String value1, String value2) {
            addCriterion("ebook_url not between", value1, value2, "ebookUrl");
            return (Criteria) this;
        }

        public Criteria andEbookPriceIsNull() {
            addCriterion("ebook_price is null");
            return (Criteria) this;
        }

        public Criteria andEbookPriceIsNotNull() {
            addCriterion("ebook_price is not null");
            return (Criteria) this;
        }

        public Criteria andEbookPriceEqualTo(String value) {
            addCriterion("ebook_price =", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceNotEqualTo(String value) {
            addCriterion("ebook_price <>", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceGreaterThan(String value) {
            addCriterion("ebook_price >", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceGreaterThanOrEqualTo(String value) {
            addCriterion("ebook_price >=", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceLessThan(String value) {
            addCriterion("ebook_price <", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceLessThanOrEqualTo(String value) {
            addCriterion("ebook_price <=", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceLike(String value) {
            addCriterion("ebook_price like", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceNotLike(String value) {
            addCriterion("ebook_price not like", value, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceIn(List<String> values) {
            addCriterion("ebook_price in", values, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceNotIn(List<String> values) {
            addCriterion("ebook_price not in", values, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceBetween(String value1, String value2) {
            addCriterion("ebook_price between", value1, value2, "ebookPrice");
            return (Criteria) this;
        }

        public Criteria andEbookPriceNotBetween(String value1, String value2) {
            addCriterion("ebook_price not between", value1, value2, "ebookPrice");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}