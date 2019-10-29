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
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检查监控项心跳状态
 * Created by Lin on 2019/10/29.
 */
@Component
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
    public void checkHeartBeatTask() {

        int cutOffTimes = Utility.getCurrentSecondTimes() - 60;

        List<HeartBeat> heartBeatList = heartBeatService.selectUnHeartBeatByUpdateTime(cutOffTimes);

        if (CollectionUtils.isEmpty(heartBeatList)) {
            return;
        }

        for (HeartBeat heartBeat : heartBeatList) {

            logger.info("checkHeartBeatTask: the heartBeat info is {}", heartBeat);

            MetricItem metricItem = metricItemService.selectByMetricKey(heartBeat.getMetricKey());

            MetricStatusRequest metricStatusRequest = new MetricStatusRequest();
            metricStatusRequest.setMetricKey(metricItem.getMetricKey());
            metricStatusRequest.setLevel(EmergencyLevelEnum.INVALID.getLevel());
            metricStatusRequest.setDesc(metricItem.getName() + " 已失效");

            logger.info("checkHeartBeatTask: start to handel metric status. " +
                    "metricItem is {}, metricStatusRequest is {}", metricItem, metricStatusRequest);

            metricStatusServiceService.handleMetricStatus(metricItem, metricStatusRequest);
        }

    }

}
