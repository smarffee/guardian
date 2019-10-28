package com.lin.dao.impl;

import com.lin.dao.MetricEmergencyEventDao;
import com.lin.dao.mapper.guardian.MetricEmergencyEventMapper;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricEmergencyEventExample;
import com.lin.util.Constant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lin on 2019/10/28.
 */
@Repository
public class MetricEmergencyEventDaoImpl implements MetricEmergencyEventDao {

    @Autowired
    private MetricEmergencyEventMapper metricEmergencyEventMapper;

    @Override
    public MetricEmergencyEvent selectUnSolvedEventByMetric(String metricGid) {

        MetricEmergencyEventExample emergencyEventExample = new MetricEmergencyEventExample();
        emergencyEventExample.createCriteria()
                .andMetricGidEqualTo(metricGid)
                .andStatusNotEqualTo(Constant.EmergencyEventStatus.SOLEVD);

        List<MetricEmergencyEvent> metricEmergencyEventList = metricEmergencyEventMapper.selectByExample(emergencyEventExample);

        return CollectionUtils.isEmpty(metricEmergencyEventList) ? null : metricEmergencyEventList.get(0);
    }

    @Override
    public int saveMetricEmergencyEvent(MetricEmergencyEvent metricEmergencyEvent) {
        return metricEmergencyEventMapper.insert(metricEmergencyEvent);
    }

    @Override
    public int updateEventAbnormalDataByGid(MetricEmergencyEvent metricEmergencyEvent) {
        return 0;
    }

}
