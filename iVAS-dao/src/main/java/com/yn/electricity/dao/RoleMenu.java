package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: RoleMenu
 * @Author: zzs
 * @Date: 2021/3/12 17:00
 * @Description: 角色-权限关系实体类
 */
@Data
@TableName(value = "role_menu")
public class RoleMenu {

    /**
     * 角色ipRoleId
     */
    @TableField(value = "ip_role_id")
    private String ipRoleId;

    /**
     * 权限id
     */
    @TableField(value = "menu_id")
    private String menuId;

}
