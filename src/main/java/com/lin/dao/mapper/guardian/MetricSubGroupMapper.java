package com.lin.dao.mapper.guardian;

import com.lin.model.db.MetricSubGroup;
import com.lin.model.db.MetricSubGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MetricSubGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int countByExample(MetricSubGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int deleteByExample(MetricSubGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int insert(MetricSubGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int insertSelective(MetricSubGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    List<MetricSubGroup> selectByExample(MetricSubGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    MetricSubGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MetricSubGroup record, @Param("example") MetricSubGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MetricSubGroup record, @Param("example") MetricSubGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MetricSubGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table metric_sub_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MetricSubGroup record);
}