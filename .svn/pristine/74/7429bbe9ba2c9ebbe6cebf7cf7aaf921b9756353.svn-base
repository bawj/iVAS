package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: RolesDepts
 * @Author: zzs
 * @Date: 2021/3/11 10:04
 * @Description: 数据权限 部门
 */
@Data
@TableName(value = "role_dept")
public class RoleDept {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色ipRoleId
     */
    private String ipRoleId;
    /**
     * 部门编码
     */
    private String depCode;
    /**
     * 数据权限类型:0全部数据,1本部门数据,2自定义
     */
    private String dataType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

}
