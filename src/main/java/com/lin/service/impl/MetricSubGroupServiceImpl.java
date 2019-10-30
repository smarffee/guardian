package com.lin.service.impl;

import com.lin.dao.MetricSubGroupDao;
import com.lin.model.db.MetricSubGroup;
import com.lin.service.MetricSubGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricSubGroupServiceImpl implements MetricSubGroupService {

    private static final Logger logger = LoggerFactory.getLogger(MetricSubGroupServiceImpl.class);

    @Autowired
    private MetricSubGroupDao metricSubGroupDao;

    @Override
    public List<MetricSubGroup> selectSubGroupByGid(List<String> subGroupGidList) {
        return metricSubGroupDao.selectSubGroupByGid(subGroupGidList);
    }

}
