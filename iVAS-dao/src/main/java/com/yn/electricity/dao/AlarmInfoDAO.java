package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 报警信息
 */
@Data
@TableName(value = "alarm_info")
public class AlarmInfoDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * 设备id
     */
    @TableField(value = "camera_id")
    private Integer cameraId;

    /**
     * 所属组织
     */
    @TableField(value = "organization_id")
    private Integer organizationId;

    /**
     * 报警时间
     */
    @TableField(value = "alarm_time")
    private Date alarmTime;

    /**
     * 报警等级 0一般 1重要 2紧急 3非常紧急
     */
    @TableField(value = "alarm_grade")
    private Integer alarmGrade;

    /**
     * 报警配置类型 alarm_configuration_type 表id 
     */
    @TableField(value = "alarm_configuration_type_id")
    private Integer alarmConfigurationTypeId;

    /**
     * 报警抓图
     */
    @TableField(value = "alarm_snapshot_url")
    private String alarmSnapshotUrl;

    /**
     * 报警内容
     */
    @TableField(value = "alarm_content")
    private String alarmContent;

    /**
     * 确认状态 0未确认 1已确认
     */
    @TableField(value = "confirm_status")
    private Boolean confirmStatus;

    /**
     * 确认人 名称
     */
    @TableField(value = "confirm_people")
    private String confirmPeople;

    /**
     * 确认时间
     */
    @TableField(value = "confirm_time")
    private Date confirmTime;

    /**
     * 备注
     */
    private String remarks;
}