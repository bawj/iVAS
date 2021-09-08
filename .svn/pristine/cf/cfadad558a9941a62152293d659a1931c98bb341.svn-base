package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author alarm_configuration_type 和 alarm_response_type 中间表
 */
@Data
@TableName(value = "act_art")
public class ActArtDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    @TableField(value = "alarm_configuration_type_id")
    private Integer alarmConfigurationTypeId;

    @TableField(value = "alarm_response_type_id")
    private Integer alarmResponseTypeId;

}