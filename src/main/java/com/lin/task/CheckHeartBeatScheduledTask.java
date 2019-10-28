package com.lin.task;

import com.lin.model.EmergencyLevelEnum;
import com.lin.model.db.HeartBeat;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.HeartBeatService;
import com.lin.service.MetricEmergencyEventService;
import com.lin.service.MetricItemService;
import com.lin.service.MetricStatusServiceService;
import com.lin.util.Constant;
import com.lin.util.Utility;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by Lin on 2019/10/29.
 */
public class CheckHeartBeatScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(CheckHeartBeatScheduledTask.class);

    @Autowired
    private HeartBeatService heartBeatService;

    @Autowired
    private MetricItemService metricItemService;

    @Autowired
    private MetricStatusServiceService metricStatusServiceService;

    /**
     * 检查没有心跳的监控项
     * CRON表达式 每5s执行一次
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void saveHistoryEmergencyEvent() {

        int currentSecondTimes = Utility.getCurrentSecondTimes();
        int beginTimes = currentSecondTimes - 60;

        List<HeartBeat> heartBeatList = heartBeatService.selectHeartBeatByUpdateTime(beginTimes, currentSecondTimes);

        if (CollectionUtils.isEmpty(heartBeatList)) {
            return;
        }

        for (HeartBeat heartBeat : heartBeatList) {
            MetricItem metricItem = metricItemService.selectByMetricKey(heartBeat.getMetricKey());

            if (metricItem.getStatus() == Constant.MetricStatus.UN_ENABLE) {
                continue;
            }

            MetricStatusRequest metricStatusRequest = new MetricStatusRequest();
            metricStatusRequest.setMetricKey(metricItem.getMetricKey());
            metricStatusRequest.setLevel(EmergencyLevelEnum.INVALID.getLevel());
            metricStatusRequest.setDesc(metricItem.getName() + "已失效");

            metricStatusServiceService.handleMetricStatus(metricItem, metricStatusRequest);
        }

    }

}
