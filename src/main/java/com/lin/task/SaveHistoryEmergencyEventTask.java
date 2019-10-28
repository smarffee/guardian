package com.lin.task;

import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricEmergencyHistoryEvent;
import com.lin.service.MetricEmergencyEventService;
import com.lin.service.MetricEmergencyHistoryEventService;
import com.lin.util.Utility;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lin on 2019/10/29.
 */
@Component
public class SaveHistoryEmergencyEventTask {

    private static final Logger logger = LoggerFactory.getLogger(SaveHistoryEmergencyEventTask.class);

    @Autowired
    private MetricEmergencyEventService metricEmergencyEventService;

    @Autowired
    private MetricEmergencyHistoryEventService metricEmergencyHistoryEventService;

    /**
     * 将已经关闭的告警, 保存到历史表中
     * CRON表达式 每5s执行一次
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void saveHistoryEmergencyEvent() {
        List<MetricEmergencyEvent> emergencyEventList = metricEmergencyEventService.selectSolvedEvent();

        if (CollectionUtils.isEmpty(emergencyEventList)) {
            return;
        }

        for (MetricEmergencyEvent metricEmergencyEvent : emergencyEventList) {

            saveHistoryEvent(createHistoryEvent(metricEmergencyEvent));

        }

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveHistoryEvent(MetricEmergencyHistoryEvent historyEvent) {

        int num = metricEmergencyEventService.deleteByGid(historyEvent.getGid());
        if (num == 1) {
            metricEmergencyHistoryEventService.saveHistoryEvent(historyEvent);
            return true;
        }

        return false;
    }

    private MetricEmergencyHistoryEvent createHistoryEvent(MetricEmergencyEvent metricEmergencyEvent) {
        MetricEmergencyHistoryEvent historyEvent = new MetricEmergencyHistoryEvent();

        historyEvent.setGid(metricEmergencyEvent.getGid());
        historyEvent.setGroupGid(metricEmergencyEvent.getGroupGid());
        historyEvent.setSubGroupGid(metricEmergencyEvent.getSubGroupGid());
        historyEvent.setCreateTime(Utility.getCurrentSecondTimes());
        historyEvent.setUpdateTime(Utility.getCurrentSecondTimes());
        historyEvent.setMetricGid(metricEmergencyEvent.getMetricGid());
        historyEvent.setBeginTime(metricEmergencyEvent.getCreateTime());
        historyEvent.setCloseTime(metricEmergencyEvent.getUpdateTime());
        historyEvent.setCloseStatus(metricEmergencyEvent.getStatus());

        return historyEvent;
    }

}
