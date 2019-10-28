package com.lin.service;

import com.lin.model.db.MetricItem;

/**
 * Created by Lin on 2019/10/29.
 */
public interface MetricItemService {

    MetricItem selectByMetricKey(String metricKey);

}
