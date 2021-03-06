package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 报警配置
 */
@Data
@TableName(value = "alarm_configuration")
public class AlarmConfigurationDAO extends BaseDAO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 报警配置类型 alarm_configuration_type 表id 
     */
    @TableField(value = "alarm_configuration_type_id")
    private Integer alarmConfigurationTypeId;

    /**
     * 布防时间 json格式存储
     */
    @TableField(value = "distribution_time")
    private String distributionTime;

    /**
     * 报警等级 0一般 1重要 2紧急 3非常紧急
     */
    @TableField(value = "alarm_grade")
    private Integer alarmGrade;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否启用 0 禁用 非0 启用
     */
    private Boolean enable;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

}