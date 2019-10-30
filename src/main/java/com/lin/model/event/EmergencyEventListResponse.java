package com.lin.model.event;

import com.lin.model.AbnormalData;

import java.util.List;

public class EmergencyEventListResponse {

    private List<EmergencyEvent> eventList;

    public static class EmergencyEvent {

        private String eventGid;

        private String metricGid;

        private String metricKey;

        private String metricName;

        private String groupGid;

        private String groupName;

        private String subGroupGid;

        private String subGroupName;

        private int startTime;

        private int level;

        private int status;

        private String desc;

        private AbnormalData abnormalData;

        /** setter/getter **/

        public String getEventGid() {
            return eventGid;
        }

        public void setEventGid(String eventGid) {
            this.eventGid = eventGid;
        }

        public String getMetricGid() {
            return metricGid;
        }

        public void setMetricGid(String metricGid) {
            this.metricGid = metricGid;
        }

        public String getMetricKey() {
            return metricKey;
        }

        public void setMetricKey(String metricKey) {
            this.metricKey = metricKey;
        }

        public String getMetricName() {
            return metricName;
        }

        public void setMetricName(String metricName) {
            this.metricName = metricName;
        }

        public String getGroupGid() {
            return groupGid;
        }

        public void setGroupGid(String groupGid) {
            this.groupGid = groupGid;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getSubGroupGid() {
            return subGroupGid;
        }

        public void setSubGroupGid(String subGroupGid) {
            this.subGroupGid = subGroupGid;
        }

        public String getSubGroupName() {
            return subGroupName;
        }

        public void setSubGroupName(String subGroupName) {
            this.subGroupName = subGroupName;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public AbnormalData getAbnormalData() {
            return abnormalData;
        }

        public void setAbnormalData(AbnormalData abnormalData) {
            this.abnormalData = abnormalData;
        }

        @Override
        public String toString() {
            return "EmergencyEvent{" +
                    "eventGid='" + eventGid + '\'' +
                    ", metricGid='" + metricGid + '\'' +
                    ", metricKey='" + metricKey + '\'' +
                    ", metricName='" + metricName + '\'' +
                    ", groupGid='" + groupGid + '\'' +
                    ", groupName='" + groupName + '\'' +
                    ", subGroupGid='" + subGroupGid + '\'' +
                    ", subGroupName='" + subGroupName + '\'' +
                    ", startTime=" + startTime +
                    ", level=" + level +
                    ", status=" + status +
                    ", desc='" + desc + '\'' +
                    ", abnormalData=" + abnormalData +
                    '}';
        }
    }

    /** setter/getter **/

    public List<EmergencyEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<EmergencyEvent> eventList) {
        this.eventList = eventList;
    }

    @Override
    public String toString() {
        return "EmergencyEventListResponse{" +
                "eventList=" + eventList +
                '}';
    }

}
