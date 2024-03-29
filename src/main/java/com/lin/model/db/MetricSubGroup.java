package com.lin.model.db;

public class MetricSubGroup {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column metric_sub_group.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column metric_sub_group.gid
     *
     * @mbggenerated
     */
    private String gid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column metric_sub_group.group_gid
     *
     * @mbggenerated
     */
    private String groupGid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column metric_sub_group.create_time
     *
     * @mbggenerated
     */
    private Integer createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column metric_sub_group.update_time
     *
     * @mbggenerated
     */
    private Integer updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column metric_sub_group.sub_group_name
     *
     * @mbggenerated
     */
    private String subGroupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column metric_sub_group.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column metric_sub_group.id
     *
     * @return the value of metric_sub_group.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column metric_sub_group.id
     *
     * @param id the value for metric_sub_group.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column metric_sub_group.gid
     *
     * @return the value of metric_sub_group.gid
     *
     * @mbggenerated
     */
    public String getGid() {
        return gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column metric_sub_group.gid
     *
     * @param gid the value for metric_sub_group.gid
     *
     * @mbggenerated
     */
    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column metric_sub_group.group_gid
     *
     * @return the value of metric_sub_group.group_gid
     *
     * @mbggenerated
     */
    public String getGroupGid() {
        return groupGid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column metric_sub_group.group_gid
     *
     * @param groupGid the value for metric_sub_group.group_gid
     *
     * @mbggenerated
     */
    public void setGroupGid(String groupGid) {
        this.groupGid = groupGid == null ? null : groupGid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column metric_sub_group.create_time
     *
     * @return the value of metric_sub_group.create_time
     *
     * @mbggenerated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column metric_sub_group.create_time
     *
     * @param createTime the value for metric_sub_group.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column metric_sub_group.update_time
     *
     * @return the value of metric_sub_group.update_time
     *
     * @mbggenerated
     */
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column metric_sub_group.update_time
     *
     * @param updateTime the value for metric_sub_group.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column metric_sub_group.sub_group_name
     *
     * @return the value of metric_sub_group.sub_group_name
     *
     * @mbggenerated
     */
    public String getSubGroupName() {
        return subGroupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column metric_sub_group.sub_group_name
     *
     * @param subGroupName the value for metric_sub_group.sub_group_name
     *
     * @mbggenerated
     */
    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName == null ? null : subGroupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column metric_sub_group.remark
     *
     * @return the value of metric_sub_group.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column metric_sub_group.remark
     *
     * @param remark the value for metric_sub_group.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}