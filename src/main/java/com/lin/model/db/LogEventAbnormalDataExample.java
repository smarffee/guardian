package com.lin.model.db;

import java.util.ArrayList;
import java.util.List;

public class LogEventAbnormalDataExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public LogEventAbnormalDataExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGidIsNull() {
            addCriterion("gid is null");
            return (Criteria) this;
        }

        public Criteria andGidIsNotNull() {
            addCriterion("gid is not null");
            return (Criteria) this;
        }

        public Criteria andGidEqualTo(String value) {
            addCriterion("gid =", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotEqualTo(String value) {
            addCriterion("gid <>", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThan(String value) {
            addCriterion("gid >", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThanOrEqualTo(String value) {
            addCriterion("gid >=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThan(String value) {
            addCriterion("gid <", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThanOrEqualTo(String value) {
            addCriterion("gid <=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLike(String value) {
            addCriterion("gid like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotLike(String value) {
            addCriterion("gid not like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidIn(List<String> values) {
            addCriterion("gid in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotIn(List<String> values) {
            addCriterion("gid not in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidBetween(String value1, String value2) {
            addCriterion("gid between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotBetween(String value1, String value2) {
            addCriterion("gid not between", value1, value2, "gid");
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

        public Criteria andCreateTimeEqualTo(Integer value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Integer value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Integer value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Integer value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Integer value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Integer> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Integer> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Integer value1, Integer value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andMetricGidIsNull() {
            addCriterion("metric_gid is null");
            return (Criteria) this;
        }

        public Criteria andMetricGidIsNotNull() {
            addCriterion("metric_gid is not null");
            return (Criteria) this;
        }

        public Criteria andMetricGidEqualTo(String value) {
            addCriterion("metric_gid =", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidNotEqualTo(String value) {
            addCriterion("metric_gid <>", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidGreaterThan(String value) {
            addCriterion("metric_gid >", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidGreaterThanOrEqualTo(String value) {
            addCriterion("metric_gid >=", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidLessThan(String value) {
            addCriterion("metric_gid <", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidLessThanOrEqualTo(String value) {
            addCriterion("metric_gid <=", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidLike(String value) {
            addCriterion("metric_gid like", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidNotLike(String value) {
            addCriterion("metric_gid not like", value, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidIn(List<String> values) {
            addCriterion("metric_gid in", values, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidNotIn(List<String> values) {
            addCriterion("metric_gid not in", values, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidBetween(String value1, String value2) {
            addCriterion("metric_gid between", value1, value2, "metricGid");
            return (Criteria) this;
        }

        public Criteria andMetricGidNotBetween(String value1, String value2) {
            addCriterion("metric_gid not between", value1, value2, "metricGid");
            return (Criteria) this;
        }

        public Criteria andEventGidIsNull() {
            addCriterion("event_gid is null");
            return (Criteria) this;
        }

        public Criteria andEventGidIsNotNull() {
            addCriterion("event_gid is not null");
            return (Criteria) this;
        }

        public Criteria andEventGidEqualTo(String value) {
            addCriterion("event_gid =", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidNotEqualTo(String value) {
            addCriterion("event_gid <>", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidGreaterThan(String value) {
            addCriterion("event_gid >", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidGreaterThanOrEqualTo(String value) {
            addCriterion("event_gid >=", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidLessThan(String value) {
            addCriterion("event_gid <", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidLessThanOrEqualTo(String value) {
            addCriterion("event_gid <=", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidLike(String value) {
            addCriterion("event_gid like", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidNotLike(String value) {
            addCriterion("event_gid not like", value, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidIn(List<String> values) {
            addCriterion("event_gid in", values, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidNotIn(List<String> values) {
            addCriterion("event_gid not in", values, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidBetween(String value1, String value2) {
            addCriterion("event_gid between", value1, value2, "eventGid");
            return (Criteria) this;
        }

        public Criteria andEventGidNotBetween(String value1, String value2) {
            addCriterion("event_gid not between", value1, value2, "eventGid");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumIsNull() {
            addCriterion("abnormal_num is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumIsNotNull() {
            addCriterion("abnormal_num is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumEqualTo(Integer value) {
            addCriterion("abnormal_num =", value, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumNotEqualTo(Integer value) {
            addCriterion("abnormal_num <>", value, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumGreaterThan(Integer value) {
            addCriterion("abnormal_num >", value, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("abnormal_num >=", value, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumLessThan(Integer value) {
            addCriterion("abnormal_num <", value, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumLessThanOrEqualTo(Integer value) {
            addCriterion("abnormal_num <=", value, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumIn(List<Integer> values) {
            addCriterion("abnormal_num in", values, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumNotIn(List<Integer> values) {
            addCriterion("abnormal_num not in", values, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumBetween(Integer value1, Integer value2) {
            addCriterion("abnormal_num between", value1, value2, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalNumNotBetween(Integer value1, Integer value2) {
            addCriterion("abnormal_num not between", value1, value2, "abnormalNum");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataIsNull() {
            addCriterion("abnormal_data is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataIsNotNull() {
            addCriterion("abnormal_data is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataEqualTo(String value) {
            addCriterion("abnormal_data =", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataNotEqualTo(String value) {
            addCriterion("abnormal_data <>", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataGreaterThan(String value) {
            addCriterion("abnormal_data >", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataGreaterThanOrEqualTo(String value) {
            addCriterion("abnormal_data >=", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataLessThan(String value) {
            addCriterion("abnormal_data <", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataLessThanOrEqualTo(String value) {
            addCriterion("abnormal_data <=", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataLike(String value) {
            addCriterion("abnormal_data like", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataNotLike(String value) {
            addCriterion("abnormal_data not like", value, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataIn(List<String> values) {
            addCriterion("abnormal_data in", values, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataNotIn(List<String> values) {
            addCriterion("abnormal_data not in", values, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataBetween(String value1, String value2) {
            addCriterion("abnormal_data between", value1, value2, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andAbnormalDataNotBetween(String value1, String value2) {
            addCriterion("abnormal_data not between", value1, value2, "abnormalData");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table log_event_abnormal_data
     *
     * @mbggenerated
     */
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