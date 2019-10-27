package com.lin.dao;

import com.lin.model.db.MetricItem;

/**
 * Created by Lin on 2019/10/27.
 */
public interface MetricItemDao {

    MetricItem selectByMetricKey(String metricKey);

}
