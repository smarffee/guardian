package com.lin.dao.impl;

import com.lin.dao.HeartBeatDao;
import com.lin.dao.mapper.guardian.HeartBeatMapper;
import com.lin.model.db.HeartBeat;
import com.lin.model.db.HeartBeatExample;
import com.lin.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lin on 2019/10/27.
 */
@Repository
public class HeartBeatDaoImpl implements HeartBeatDao {

    @Autowired
    private HeartBeatMapper heartBeatMapper;

    @Override
    public void heartBeatByKey(String metricKey) {

        HeartBeat heartBeat = new HeartBeat();
        heartBeat.setUpdateTime(Utility.getCurrentSecondTimes());

        HeartBeatExample example = new HeartBeatExample();
        example.createCriteria().andMetricKeyEqualTo(metricKey);

        heartBeatMapper.updateByExampleSelective(heartBeat, example);
    }

    @Override
    public int saveHeartBeat(HeartBeat heartBeat) {
        return heartBeatMapper.insert(heartBeat);
    }

    @Override
    public List<HeartBeat> selectHeartBeatByUpdateTime(int beginTimes, int endTimes) {

        HeartBeatExample example = new HeartBeatExample();
        example.createCriteria()
                .andUpdateTimeBetween(beginTimes, endTimes);

        return heartBeatMapper.selectByExample(example);
    }
}
