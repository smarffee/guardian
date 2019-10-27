package com.lin.service.impl;

import com.lin.dao.MetricItemDao;
import com.lin.model.EmergencyLevelEnum;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.MetricEmergencyEventService;
import com.lin.service.MetricStatusServiceService;
import com.lin.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lin on 2019/10/27.
 */
@Service
public class MetricStatusServiceImpl implements MetricStatusServiceService {

    @Autowired
    private MetricEmergencyEventService metricEmergencyEventService;

    @Autowired
    private MetricItemDao metricItemDao;

    @Override
    public void handleMetricStatus(MetricStatusRequest metricStatusRequest) {
        //如果监控指标状态正常
        if (metricStatusRequest.getLevel() == EmergencyLevelEnum.OK.getLevel()) {
            return;
        }

        MetricItem metricItem = metricItemDao.selectByMetricKey(metricStatusRequest.getMetricKey());
        //如果没有此监控指标或者监控指标没有启用
        if (metricItem == null ||
                metricItem.getStatus() != Constant.MetricStatus.ENABLE) {
            return;
        }

        //查询当前告警事件状态
        MetricEmergencyEvent metricEmergencyEvent = metricEmergencyEventService.selectUnSolvedEventByMetric(metricStatusRequest.getMetricKey());

        //告警事件状态没有改变
        if (!emergencyEventChanged(metricEmergencyEvent, metricStatusRequest)) {
            return;
        }

        //告警事件状态便更
        metricEmergencyEventService.emergencyEventChanged(metricEmergencyEvent, metricItem, metricStatusRequest);
    }

    private boolean emergencyEventChanged(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
        if (metricEmergencyEvent == null) {
            return true;
        }
        //监控等级发生改变
        if (metricEmergencyEvent.getLevel() != metricStatusRequest.getLevel()) {
            return true;
        }
        //异常数据数量发生改变
        if (metricEmergencyEvent.getAbnormalNum() != metricStatusRequest.getAbnormalNum()) {
            return true;
        }

        return false;
    }

}
