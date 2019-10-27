package com.lin.model.status;

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

    public static class AbnormalData {

        private String caption; //标题

        private List<String> title; //表头

        private List<List<Object>> data; //异常数据

        /** setter/getter **/

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public List<String> getTitle() {
            return title;
        }

        public void setTitle(List<String> title) {
            this.title = title;
        }

        public List<List<Object>> getData() {
            return data;
        }

        public void setData(List<List<Object>> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "AbnormalData{" +
                    "caption='" + caption + '\'' +
                    ", title=" + title +
                    ", data=" + data +
                    '}';
        }
    }

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
