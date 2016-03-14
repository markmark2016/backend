package com.mark.backend.mysql.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupExample() {
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

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andGuaranteeIsNull() {
            addCriterion("guarantee is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeIsNotNull() {
            addCriterion("guarantee is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeEqualTo(Integer value) {
            addCriterion("guarantee =", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeNotEqualTo(Integer value) {
            addCriterion("guarantee <>", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGreaterThan(Integer value) {
            addCriterion("guarantee >", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGreaterThanOrEqualTo(Integer value) {
            addCriterion("guarantee >=", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeLessThan(Integer value) {
            addCriterion("guarantee <", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeLessThanOrEqualTo(Integer value) {
            addCriterion("guarantee <=", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeIn(List<Integer> values) {
            addCriterion("guarantee in", values, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeNotIn(List<Integer> values) {
            addCriterion("guarantee not in", values, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeBetween(Integer value1, Integer value2) {
            addCriterion("guarantee between", value1, value2, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeNotBetween(Integer value1, Integer value2) {
            addCriterion("guarantee not between", value1, value2, "guarantee");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNull() {
            addCriterion("frequency is null");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNotNull() {
            addCriterion("frequency is not null");
            return (Criteria) this;
        }

        public Criteria andFrequencyEqualTo(String value) {
            addCriterion("frequency =", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotEqualTo(String value) {
            addCriterion("frequency <>", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThan(String value) {
            addCriterion("frequency >", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("frequency >=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThan(String value) {
            addCriterion("frequency <", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThanOrEqualTo(String value) {
            addCriterion("frequency <=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLike(String value) {
            addCriterion("frequency like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotLike(String value) {
            addCriterion("frequency not like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyIn(List<String> values) {
            addCriterion("frequency in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotIn(List<String> values) {
            addCriterion("frequency not in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyBetween(String value1, String value2) {
            addCriterion("frequency between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotBetween(String value1, String value2) {
            addCriterion("frequency not between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andLatestTimeIsNull() {
            addCriterion("latest_time is null");
            return (Criteria) this;
        }

        public Criteria andLatestTimeIsNotNull() {
            addCriterion("latest_time is not null");
            return (Criteria) this;
        }

        public Criteria andLatestTimeEqualTo(Date value) {
            addCriterion("latest_time =", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeNotEqualTo(Date value) {
            addCriterion("latest_time <>", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeGreaterThan(Date value) {
            addCriterion("latest_time >", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("latest_time >=", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeLessThan(Date value) {
            addCriterion("latest_time <", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeLessThanOrEqualTo(Date value) {
            addCriterion("latest_time <=", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeIn(List<Date> values) {
            addCriterion("latest_time in", values, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeNotIn(List<Date> values) {
            addCriterion("latest_time not in", values, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeBetween(Date value1, Date value2) {
            addCriterion("latest_time between", value1, value2, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeNotBetween(Date value1, Date value2) {
            addCriterion("latest_time not between", value1, value2, "latestTime");
            return (Criteria) this;
        }

        public Criteria andBookIdFkIsNull() {
            addCriterion("book_id_fk is null");
            return (Criteria) this;
        }

        public Criteria andBookIdFkIsNotNull() {
            addCriterion("book_id_fk is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdFkEqualTo(String value) {
            addCriterion("book_id_fk =", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkNotEqualTo(String value) {
            addCriterion("book_id_fk <>", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkGreaterThan(String value) {
            addCriterion("book_id_fk >", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkGreaterThanOrEqualTo(String value) {
            addCriterion("book_id_fk >=", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkLessThan(String value) {
            addCriterion("book_id_fk <", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkLessThanOrEqualTo(String value) {
            addCriterion("book_id_fk <=", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkLike(String value) {
            addCriterion("book_id_fk like", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkNotLike(String value) {
            addCriterion("book_id_fk not like", value, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkIn(List<String> values) {
            addCriterion("book_id_fk in", values, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkNotIn(List<String> values) {
            addCriterion("book_id_fk not in", values, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkBetween(String value1, String value2) {
            addCriterion("book_id_fk between", value1, value2, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andBookIdFkNotBetween(String value1, String value2) {
            addCriterion("book_id_fk not between", value1, value2, "bookIdFk");
            return (Criteria) this;
        }

        public Criteria andReadSloganIsNull() {
            addCriterion("read_slogan is null");
            return (Criteria) this;
        }

        public Criteria andReadSloganIsNotNull() {
            addCriterion("read_slogan is not null");
            return (Criteria) this;
        }

        public Criteria andReadSloganEqualTo(String value) {
            addCriterion("read_slogan =", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganNotEqualTo(String value) {
            addCriterion("read_slogan <>", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganGreaterThan(String value) {
            addCriterion("read_slogan >", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganGreaterThanOrEqualTo(String value) {
            addCriterion("read_slogan >=", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganLessThan(String value) {
            addCriterion("read_slogan <", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganLessThanOrEqualTo(String value) {
            addCriterion("read_slogan <=", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganLike(String value) {
            addCriterion("read_slogan like", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganNotLike(String value) {
            addCriterion("read_slogan not like", value, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganIn(List<String> values) {
            addCriterion("read_slogan in", values, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganNotIn(List<String> values) {
            addCriterion("read_slogan not in", values, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganBetween(String value1, String value2) {
            addCriterion("read_slogan between", value1, value2, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andReadSloganNotBetween(String value1, String value2) {
            addCriterion("read_slogan not between", value1, value2, "readSlogan");
            return (Criteria) this;
        }

        public Criteria andGroupDescIsNull() {
            addCriterion("group_desc is null");
            return (Criteria) this;
        }

        public Criteria andGroupDescIsNotNull() {
            addCriterion("group_desc is not null");
            return (Criteria) this;
        }

        public Criteria andGroupDescEqualTo(String value) {
            addCriterion("group_desc =", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotEqualTo(String value) {
            addCriterion("group_desc <>", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescGreaterThan(String value) {
            addCriterion("group_desc >", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescGreaterThanOrEqualTo(String value) {
            addCriterion("group_desc >=", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescLessThan(String value) {
            addCriterion("group_desc <", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescLessThanOrEqualTo(String value) {
            addCriterion("group_desc <=", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescLike(String value) {
            addCriterion("group_desc like", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotLike(String value) {
            addCriterion("group_desc not like", value, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescIn(List<String> values) {
            addCriterion("group_desc in", values, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotIn(List<String> values) {
            addCriterion("group_desc not in", values, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescBetween(String value1, String value2) {
            addCriterion("group_desc between", value1, value2, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andGroupDescNotBetween(String value1, String value2) {
            addCriterion("group_desc not between", value1, value2, "groupDesc");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefIsNull() {
            addCriterion("captain_brief is null");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefIsNotNull() {
            addCriterion("captain_brief is not null");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefEqualTo(String value) {
            addCriterion("captain_brief =", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefNotEqualTo(String value) {
            addCriterion("captain_brief <>", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefGreaterThan(String value) {
            addCriterion("captain_brief >", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefGreaterThanOrEqualTo(String value) {
            addCriterion("captain_brief >=", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefLessThan(String value) {
            addCriterion("captain_brief <", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefLessThanOrEqualTo(String value) {
            addCriterion("captain_brief <=", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefLike(String value) {
            addCriterion("captain_brief like", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefNotLike(String value) {
            addCriterion("captain_brief not like", value, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefIn(List<String> values) {
            addCriterion("captain_brief in", values, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefNotIn(List<String> values) {
            addCriterion("captain_brief not in", values, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefBetween(String value1, String value2) {
            addCriterion("captain_brief between", value1, value2, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andCaptainBriefNotBetween(String value1, String value2) {
            addCriterion("captain_brief not between", value1, value2, "captainBrief");
            return (Criteria) this;
        }

        public Criteria andUserIdFkIsNull() {
            addCriterion("user_id_fk is null");
            return (Criteria) this;
        }

        public Criteria andUserIdFkIsNotNull() {
            addCriterion("user_id_fk is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdFkEqualTo(Long value) {
            addCriterion("user_id_fk =", value, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkNotEqualTo(Long value) {
            addCriterion("user_id_fk <>", value, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkGreaterThan(Long value) {
            addCriterion("user_id_fk >", value, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id_fk >=", value, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkLessThan(Long value) {
            addCriterion("user_id_fk <", value, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkLessThanOrEqualTo(Long value) {
            addCriterion("user_id_fk <=", value, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkIn(List<Long> values) {
            addCriterion("user_id_fk in", values, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkNotIn(List<Long> values) {
            addCriterion("user_id_fk not in", values, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkBetween(Long value1, Long value2) {
            addCriterion("user_id_fk between", value1, value2, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andUserIdFkNotBetween(Long value1, Long value2) {
            addCriterion("user_id_fk not between", value1, value2, "userIdFk");
            return (Criteria) this;
        }

        public Criteria andBookBriefIsNull() {
            addCriterion("book_brief is null");
            return (Criteria) this;
        }

        public Criteria andBookBriefIsNotNull() {
            addCriterion("book_brief is not null");
            return (Criteria) this;
        }

        public Criteria andBookBriefEqualTo(String value) {
            addCriterion("book_brief =", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefNotEqualTo(String value) {
            addCriterion("book_brief <>", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefGreaterThan(String value) {
            addCriterion("book_brief >", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefGreaterThanOrEqualTo(String value) {
            addCriterion("book_brief >=", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefLessThan(String value) {
            addCriterion("book_brief <", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefLessThanOrEqualTo(String value) {
            addCriterion("book_brief <=", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefLike(String value) {
            addCriterion("book_brief like", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefNotLike(String value) {
            addCriterion("book_brief not like", value, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefIn(List<String> values) {
            addCriterion("book_brief in", values, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefNotIn(List<String> values) {
            addCriterion("book_brief not in", values, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefBetween(String value1, String value2) {
            addCriterion("book_brief between", value1, value2, "bookBrief");
            return (Criteria) this;
        }

        public Criteria andBookBriefNotBetween(String value1, String value2) {
            addCriterion("book_brief not between", value1, value2, "bookBrief");
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

        public Criteria andGroupModeIsNull() {
            addCriterion("group_mode is null");
            return (Criteria) this;
        }

        public Criteria andGroupModeIsNotNull() {
            addCriterion("group_mode is not null");
            return (Criteria) this;
        }

        public Criteria andGroupModeEqualTo(String value) {
            addCriterion("group_mode =", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeNotEqualTo(String value) {
            addCriterion("group_mode <>", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeGreaterThan(String value) {
            addCriterion("group_mode >", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeGreaterThanOrEqualTo(String value) {
            addCriterion("group_mode >=", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeLessThan(String value) {
            addCriterion("group_mode <", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeLessThanOrEqualTo(String value) {
            addCriterion("group_mode <=", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeLike(String value) {
            addCriterion("group_mode like", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeNotLike(String value) {
            addCriterion("group_mode not like", value, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeIn(List<String> values) {
            addCriterion("group_mode in", values, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeNotIn(List<String> values) {
            addCriterion("group_mode not in", values, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeBetween(String value1, String value2) {
            addCriterion("group_mode between", value1, value2, "groupMode");
            return (Criteria) this;
        }

        public Criteria andGroupModeNotBetween(String value1, String value2) {
            addCriterion("group_mode not between", value1, value2, "groupMode");
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