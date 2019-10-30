package com.lin.dao.impl;

import com.lin.dao.MetricItemDao;
import com.lin.dao.mapper.guardian.MetricItemMapper;
import com.lin.model.db.MetricItem;
import com.lin.model.db.MetricItemExample;
import com.lin.util.Constant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lin on 2019/10/27.
 */
@Repository
public class MetricItemDaoImpl implements MetricItemDao {

    @Autowired
    private MetricItemMapper metricItemMapper;

    @Override
    public MetricItem selectByMetricKey(String metricKey) {

        MetricItemExample example = new MetricItemExample();
        example.createCriteria()
                .andMetricKeyEqualTo(metricKey)
                .andStatusEqualTo(Constant.MetricStatus.ENABLE)
                .andDeletedEqualTo(Constant.YesOrNo.NO);

        List<MetricItem> metricItemList = metricItemMapper.selectByExample(example);

        return CollectionUtils.isEmpty(metricItemList) ? null : metricItemList.get(0);
    }

    @Override
    public List<MetricItem> selectByMetricGid(List<String> metricGid) {

        MetricItemExample example = new MetricItemExample();
        example.createCriteria()
                .andGidIn(metricGid);

        return metricItemMapper.selectByExample(example);
    }

}
