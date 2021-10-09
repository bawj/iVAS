package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 平台表
 */
@Data
@TableName(value = "platform")
public class PlatformDAO extends BaseDAO{
    /**
     * 主键
     */
    @TableId(value = "id" , type = IdType.AUTO)
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
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

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
     * 状态（0下线,1上线）
     */
    private Short online;

    /**
     * 国标编码
     */
    private String code;

    /**
     * company 表中 code
     */
   @TableField(value = "company_code")
    private String companyCode;

    /**
     * department 表中 code
     */
   @TableField(value = "department_code")
    private String departmentCode;

    /**
     * user 表中的 ip_id
     */
   @TableField(value = "user_ip_id")
    private String userIpId;

    /**
     * 创建时间
     */
   @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
   @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 最后修改人
     */
   @TableField(value = "update_user_id")
    private Integer updateUserId;
}