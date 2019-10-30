package com.lin.service.impl;

import com.lin.dao.MetricGroupDao;
import com.lin.model.db.MetricGroup;
import com.lin.service.MetricGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricGroupServiceImpl implements MetricGroupService {

    private static final Logger logger = LoggerFactory.getLogger(MetricGroupServiceImpl.class);

    @Autowired
    private MetricGroupDao metricGroupDao;

    @Override
    public List<MetricGroup> selectGroupByGid(List<String> groupGidList) {
        return metricGroupDao.selectGroupByGid(groupGidList);
    }
}
