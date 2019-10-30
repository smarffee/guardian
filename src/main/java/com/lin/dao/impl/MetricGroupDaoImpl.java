package com.lin.dao.impl;

import com.lin.dao.MetricGroupDao;
import com.lin.dao.mapper.guardian.MetricGroupMapper;
import com.lin.model.db.MetricGroup;
import com.lin.model.db.MetricGroupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetricGroupDaoImpl implements MetricGroupDao {

    @Autowired
    private MetricGroupMapper metricGroupMapper;

    @Override
    public List<MetricGroup> selectGroupByGid(List<String> groupGidList) {

        MetricGroupExample example = new MetricGroupExample();
        example.createCriteria()
                .andGidIn(groupGidList);

        return metricGroupMapper.selectByExample(example);
    }

}
