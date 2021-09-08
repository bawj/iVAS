package com.yn.electricity.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: RoleMenuSO
 * @Author: zzs
 * @Date: 2021/3/4 9:23
 * @Description:
 */
public class RoleMenuSO implements Serializable {
    private static final long serialVersionUID = 344468977352231396L;
    /**
     * 自增
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 权限
     */
    private List<MenuSO> menuList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<MenuSO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuSO> menuList) {
        this.menuList = menuList;
    }
}
