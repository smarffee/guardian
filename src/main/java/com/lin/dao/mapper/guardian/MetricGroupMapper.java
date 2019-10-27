package com.lin.dao.mapper.guardian;

import com.lin.model.db.MetricGroup;
import com.lin.model.db.MetricGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MetricGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int countByExample(MetricGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int deleteByExample(MetricGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int insert(MetricGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int insertSelective(MetricGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    List<MetricGroup> selectByExample(MetricGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    MetricGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MetricGroup record, @Param("example") MetricGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MetricGroup record, @Param("example") MetricGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MetricGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MetricGroup record);
}