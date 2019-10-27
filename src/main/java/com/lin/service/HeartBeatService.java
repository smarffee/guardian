package com.lin.service;

import com.lin.model.status.MetricStatusRequest;
import org.springframework.stereotype.Service;

/**
 * Created by Lin on 2019/10/27.
 */
public interface HeartBeatService {

    /**
     * 心跳
     *
     * @param metricStatusRequest
     */
    void doHeartBeat(MetricStatusRequest metricStatusRequest);

}
