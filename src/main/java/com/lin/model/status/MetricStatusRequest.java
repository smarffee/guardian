package com.lin.model.status;

import com.lin.model.AbnormalData;

import java.util.List;

/**
 * Created by Lin on 2019/10/27.
 */
public class MetricStatusRequest {

    private String metricKey; //监控项key

    private int level; //告警级别

    private String desc; //告警描述

    private int abnormalNum; //异常数据数量

    private AbnormalData abnormalData;

    /** setter/getter **/

    public String getMetricKey() {
        return metricKey;
    }

    public void setMetricKey(String metricKey) {
        this.metricKey = metricKey;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAbnormalNum() {
        return abnormalNum;
    }

    public void setAbnormalNum(int abnormalNum) {
        this.abnormalNum = abnormalNum;
    }

    public AbnormalData getAbnormalData() {
        return abnormalData;
    }

    public void setAbnormalData(AbnormalData abnormalData) {
        this.abnormalData = abnormalData;
    }

    @Override
    public String toString() {
        return "MetricStatusRequest{" +
                "metricKey='" + metricKey + '\'' +
                ", level=" + level +
                ", desc='" + desc + '\'' +
                ", abnormalNum=" + abnormalNum +
                ", abnormalData=" + abnormalData +
                '}';
    }
}
