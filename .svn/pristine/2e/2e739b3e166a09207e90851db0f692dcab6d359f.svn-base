package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 响应类型
 */
@Data
@TableName(value = "alarm_response_type")
public class AlarmResponseTypeDAO {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * 报警响应类型名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 添加时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}