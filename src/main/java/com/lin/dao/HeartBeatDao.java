package com.lin.dao;

import com.lin.model.db.HeartBeat;

/**
 * Created by Lin on 2019/10/27.
 */
public interface HeartBeatDao {

    void heartBeatByKey(String metricKey);

    void insertHeartBeat(HeartBeat heartBeat);

}
