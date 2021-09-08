package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yn.electricity.enums.MenuTypeEnum;
import lombok.Data;

import java.util.Date;


/**
 * @ClassName: Permission
 * @Author: zzs
 * @Date: 2021/1/21 10:02
 * @Description: 权限实体类
 */
@Data
@TableName(value = "menu")
public class Menu {

    /**
     * 自增
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 权限名称
     */
    @TableField(value = "menu_name")
    private String menuName;
    /**
     * 权限路由
     */
    @TableField(value = "menu_route")
    private String menuRoute;
    /**
     * 父Code
     */
    @TableField(value = "parent_code")
    private String parentCode;
    /**
     * 节点位置 提供枚举
     * {@link MenuTypeEnum}
     */
    @TableField(value = "menu_type")
    private String menuType;
    /**
     * 路由类型
     */
    @TableField(value = "route_type")
    private String routeType;
    /**
     * 是否可用，y是n否
     */
    private String available;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private Date createTime;

}
