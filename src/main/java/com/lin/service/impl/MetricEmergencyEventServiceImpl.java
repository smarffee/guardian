package com.lin.service.impl;

import com.google.gson.Gson;
import com.lin.dao.MetricEmergencyEventDao;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.MetricEmergencyEventService;
import com.lin.util.Constant;
import com.lin.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lin on 2019/10/28.
 */
@Service
public class MetricEmergencyEventServiceImpl implements MetricEmergencyEventService {

    @Autowired
    private MetricEmergencyEventDao metricEmergencyEventDao;

    @Override
    public void emergencyEventChanged(MetricEmergencyEvent currentEvent, MetricItem metricItem, MetricStatusRequest metricStatusRequest) {

        if (currentEvent == null) {
            saveEvent(metricItem, metricStatusRequest);
        } else {

            


        }

    }

    private void saveEvent(MetricItem metricItem, MetricStatusRequest metricStatusRequest) {
        int currentTimeStamp = Utility.getCurrentTimeStamp();

        MetricEmergencyEvent metricEmergencyEvent = new MetricEmergencyEvent();
        metricEmergencyEvent.setGid(Utility.generate32UUID());
        metricEmergencyEvent.setGroupGid(metricItem.getGroupGid());
        metricEmergencyEvent.setSubGroupGid(metricItem.getSubGroupGid());
        metricEmergencyEvent.setCreateTime(currentTimeStamp);
        metricEmergencyEvent.setUpdateTime(currentTimeStamp);
        metricEmergencyEvent.setMetricKey(metricStatusRequest.getMetricKey());
        metricEmergencyEvent.setStatus(Constant.EmergencyEventStatus.UN_SOLVE);
        metricEmergencyEvent.setLevel(metricStatusRequest.getLevel());
        metricEmergencyEvent.setDescription(metricStatusRequest.getDesc());
        metricEmergencyEvent.setAbnormalNum(metricStatusRequest.getAbnormalNum());
        metricEmergencyEvent.setAbnormalData(new Gson().toJson(metricStatusRequest.getAbnormalData()));

        metricEmergencyEventDao.saveMetricEmergencyEvent(metricEmergencyEvent);
    }

    @Override
    public MetricEmergencyEvent selectUnSolvedEventByMetric(String metricKey) {
        return metricEmergencyEventDao.selectUnSolvedEventByMetric(metricKey);
    }

}
