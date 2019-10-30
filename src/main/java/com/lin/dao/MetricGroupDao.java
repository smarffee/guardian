package com.lin.dao;

import com.lin.model.db.MetricGroup;

import java.util.List;

public interface MetricGroupDao {

    List<MetricGroup> selectGroupByGid(List<String> groupGidList);

}
