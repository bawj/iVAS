package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author 设备时间自动同步 定时任务 参数
 */
@Data
@TableName(value = "system_configuration")
public class SystemConfigurationDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * 同步时间间隔 单位天
     */
    @TableField(value = "date_interval")
    private Integer dateInterval;

    /**
     * 是否启用 0 禁用 非0 启用
     */
    private Boolean enable;

}