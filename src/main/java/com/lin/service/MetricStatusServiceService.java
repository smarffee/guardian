package com.lin.service;

import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;

/**
 * Created by Lin on 2019/10/27.
 */
public interface MetricStatusServiceService {

    /**
     * 处理监控状态请求
     *
     * @param metricStatusRequest
     */
    void handleMetricStatus(MetricItem metricItem, MetricStatusRequest metricStatusRequest);

}
