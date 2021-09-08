package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Role
 * @Author: zzs
 * @Date: 2021/1/21 9:55
 * @Description: 角色实体类
 */
@Data
@TableName(value = "role")
public class Role {
    /**
     * 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * ipRoleId 唯一
     */
    private String ipRoleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 是否可用  提供枚举： @link YesOrNotEnum
     */
    private String available;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 描述
     */
    private String description;
    /**
     * 扩展字段
     */
    private String ext;


}
