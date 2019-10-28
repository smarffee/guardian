package com.lin.service.impl;

import com.lin.dao.MetricItemDao;
import com.lin.model.db.MetricItem;
import com.lin.service.MetricItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lin on 2019/10/29.
 */
@Service
public class MetricItemServiceImpl implements MetricItemService {

    @Autowired
    private MetricItemDao metricItemDao;

    @Override
    public MetricItem selectByMetricKey(String metricKey) {
        return metricItemDao.selectByMetricKey(metricKey);
    }

}
