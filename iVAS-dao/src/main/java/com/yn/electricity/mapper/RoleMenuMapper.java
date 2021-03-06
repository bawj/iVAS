package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: RoleMenuMapper
 * @Author: zzs
 * @Date: 2021/3/12 17:02
 * @Description:
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 新增角色权限关系
     * @param list
     * @param ipRoleId
     * @return
     */
    int insertList(@Param("list") List<String> list, @Param("ipRoleId") String ipRoleId);

    /**
     * 根据menuId删除角色权限关系表
     * @param list
     * @return
     */
    int deleteByMenuId(List<String> list);

    /**
     * 根据角色删除权限
     * @param list
     * @return
     */
    int deleteByIpRoleId(List<String> list);

    /**
     * 查询角色下的权限
     * @param ipRoleId
     * @return
     */
    List<String> selectByIpRoleId(String ipRoleId);
}
