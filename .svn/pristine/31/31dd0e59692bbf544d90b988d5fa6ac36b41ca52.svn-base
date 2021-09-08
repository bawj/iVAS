package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author alarm_response_type 和 alarm_configuration 中间表
 */
@Data
@TableName(value = "art_ac")
public class ArtAcDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * alarm_response_type 主键
     */
    @TableField(value = "alarm_response_type_id")
    private Integer alarmResponseTypeId;

    /**
     * alarm_configuration表主键
     */
    @TableField(value = "alarm_configuration_id")
    private Integer alarmConfigurationId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}