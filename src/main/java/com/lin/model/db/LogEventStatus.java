package com.lin.model.db;

public class LogEventStatus {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.gid
     *
     * @mbggenerated
     */
    private String gid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.create_time
     *
     * @mbggenerated
     */
    private Integer createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.metric_gid
     *
     * @mbggenerated
     */
    private String metricGid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.event_gid
     *
     * @mbggenerated
     */
    private String eventGid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.operator
     *
     * @mbggenerated
     */
    private String operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_event_status.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.id
     *
     * @return the value of log_event_status.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.id
     *
     * @param id the value for log_event_status.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.gid
     *
     * @return the value of log_event_status.gid
     *
     * @mbggenerated
     */
    public String getGid() {
        return gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.gid
     *
     * @param gid the value for log_event_status.gid
     *
     * @mbggenerated
     */
    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.create_time
     *
     * @return the value of log_event_status.create_time
     *
     * @mbggenerated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.create_time
     *
     * @param createTime the value for log_event_status.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.metric_gid
     *
     * @return the value of log_event_status.metric_gid
     *
     * @mbggenerated
     */
    public String getMetricGid() {
        return metricGid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.metric_gid
     *
     * @param metricGid the value for log_event_status.metric_gid
     *
     * @mbggenerated
     */
    public void setMetricGid(String metricGid) {
        this.metricGid = metricGid == null ? null : metricGid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.event_gid
     *
     * @return the value of log_event_status.event_gid
     *
     * @mbggenerated
     */
    public String getEventGid() {
        return eventGid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.event_gid
     *
     * @param eventGid the value for log_event_status.event_gid
     *
     * @mbggenerated
     */
    public void setEventGid(String eventGid) {
        this.eventGid = eventGid == null ? null : eventGid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.status
     *
     * @return the value of log_event_status.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.status
     *
     * @param status the value for log_event_status.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.operator
     *
     * @return the value of log_event_status.operator
     *
     * @mbggenerated
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.operator
     *
     * @param operator the value for log_event_status.operator
     *
     * @mbggenerated
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_event_status.remark
     *
     * @return the value of log_event_status.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_event_status.remark
     *
     * @param remark the value for log_event_status.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}