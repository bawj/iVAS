package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author 镜头组表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "camera_group")
public class CameraGroupDAO extends BaseDAO{
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 镜头组名称
     */
    private String name;

    /**
     * camera_group 表中的id 0代表最顶层
     */
    @TableField(value = "camera_group_id")
    private Integer cameraGroupId;

    /**
     * 排序
     */
    private Integer sport;

    /**
     * 添加时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

}