package com.lin.dao;

import com.lin.model.db.MetricItem;

import java.util.List;

/**
 * Created by Lin on 2019/10/27.
 */
public interface MetricItemDao {

    MetricItem selectByMetricKey(String metricKey);

    List<MetricItem> selectByMetricGid(List<String> metricGid);

}
