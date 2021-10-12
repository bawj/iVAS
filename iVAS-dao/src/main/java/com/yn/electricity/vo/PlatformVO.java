package com.yn.electricity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author admin
 * Date 2021/3/17 15:14
 * Description 查询设备信息
 **/
@Data
public class PlatformVO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 服务id
     */
    private Integer serviceId;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备类型id
     */
    private Integer deviceTypeId;

    /**
     * 设备类型名称
     */
    private String deviceTypeName;

    /**
     * 接入服务名称
     */
    private String serviceName;

    /**
     * 注册IP
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    private Integer online;

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
    @TableField(value = "create_Time")
    private Date createTime;

}
