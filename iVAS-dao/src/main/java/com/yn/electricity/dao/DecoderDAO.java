package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 解码器
 */
@Data
@TableName(value = "decoder")
public class DecoderDAO {
    /**
     * 主键id
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 注册IP
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    @TableField(value = "device_type_id")
    private Integer deviceTypeId;

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
     * 设备编码
     */
    @TableField(value = "decoder_code")
    private String decoderCode;

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