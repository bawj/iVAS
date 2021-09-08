package com.yn.electricity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 设备
 */
@Data
public class DeviceResultVO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备类型 device_type 主键
     */
    private Integer deviceTypeId;

    /**
     * 接入服务id  service 表主键
     */
    private Integer serviceId;

    /**
     * 所属组织id organization 表主键
     */
    private Integer organizationId;

    /**
     * 所属镜头组id camera_group表主键
     */
    private Integer cameraGroupId;

    /**
     * 注册IP
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    /**
     * 注册账号
     */
    private String registerAccount;

    /**
     * 注册密码
     */
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 表示是设备信息
     */
    private String type = "DEVICE";

}