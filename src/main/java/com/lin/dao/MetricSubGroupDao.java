package com.lin.dao;

import com.lin.model.db.MetricSubGroup;

import java.util.List;

public interface MetricSubGroupDao {

    List<MetricSubGroup> selectSubGroupByGid(List<String> subGroupGidList);

}
