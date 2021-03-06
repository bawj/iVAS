package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.beans.Transient;
import java.util.Date;

/**
 * @author 设备
 */
@Data
@TableName(value = "device")
@EqualsAndHashCode(callSuper = true)
public class DeviceDAO extends BaseDAO{
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备类型 device_type 主键
     */
    @TableField(value = "device_type_id")
    private Integer deviceTypeId;

    @TableField(exist = false)
    private String deviceType;

    /**
     * 接入服务id  service 表主键
     */
    @TableField(value = "service_id")
    private Integer serviceId;

    /**
     * 所属组织id organization 表主键
     */
    @TableField(value = "organization_id")
    private Integer organizationId;

    /**
     * 所属镜头组id camera_group表主键
     */
    @TableField(value = "camera_group_id")
    private Integer cameraGroupId;

    /**
     * 注册IP
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 注册账号
     */
    @TableField(value = "register_account")
    private String registerAccount;

    /**
     * 注册密码
     */
    @TableField(value = "register_password")
    private String registerPassword;

    /**
     * 国标编码
     */
    private String code;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    private Integer online;

    /**
     * 创建时间
     */
    @TableField(value = "create_Time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

}