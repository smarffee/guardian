package com.lin.service;

import com.lin.model.db.MetricSubGroup;

import java.util.List;

public interface MetricSubGroupService {

    List<MetricSubGroup> selectSubGroupByGid(List<String> subGroupGidList);

}
