package com.lin.dao.impl;

import com.lin.dao.MetricSubGroupDao;
import com.lin.dao.mapper.guardian.MetricSubGroupMapper;
import com.lin.model.db.MetricSubGroup;
import com.lin.model.db.MetricSubGroupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetricSubGroupDaoImpl implements MetricSubGroupDao {

    @Autowired
    private MetricSubGroupMapper metricSubGroupMapper;

    @Override
    public List<MetricSubGroup> selectSubGroupByGid(List<String> subGroupGidList) {

        MetricSubGroupExample example = new MetricSubGroupExample();
        example.createCriteria()
                .andGidIn(subGroupGidList);

        return metricSubGroupMapper.selectByExample(example);
    }

}
