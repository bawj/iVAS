package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 平台日志
 */
@Data
@TableName(value = "platform_log_info")
public class PlatformLogInfoDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * 设备id
     */
    @TableField(value = "platform_id")
    private Integer platformId;

    /**
     * 状态（0下线,1上线）
     */
    private Short online;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

}