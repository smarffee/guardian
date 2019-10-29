package com.lin.service;

import com.lin.model.db.HeartBeat;
import com.lin.model.status.MetricStatusRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 创建心跳记录
     * @param heartBeat
     * @return
     */
    int createHeartBeat(HeartBeat heartBeat);

    /**
     * 根据更新事件查询心跳
     *
     * @param cutOffTimes
     * @return
     */
    List<HeartBeat> selectUnHeartBeatByUpdateTime(int cutOffTimes);
}
