package com.lin.service;

import com.lin.model.db.MetricGroup;

import java.util.List;

public interface MetricGroupService {

    List<MetricGroup> selectGroupByGid(List<String> groupGidList);

}
