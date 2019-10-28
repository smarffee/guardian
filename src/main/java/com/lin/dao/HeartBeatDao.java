package com.lin.dao;

import com.lin.model.db.HeartBeat;

import java.util.List;

/**
 * Created by Lin on 2019/10/27.
 */
public interface HeartBeatDao {

    void heartBeatByKey(String metricKey);

    int saveHeartBeat(HeartBeat heartBeat);

    List<HeartBeat> selectHeartBeatByUpdateTime(int beginTimes, int endTimes);
}
