package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.RoleDept;

import java.util.List;

/**
 * @ClassName: RoleDeptMapper
 * @Author: zzs
 * @Date: 2021/3/14 9:36
 * @Description: 数据权限-角色-部门
 */
public interface RoleDeptMapper extends BaseMapper<RoleDept> {

    /**
     * 新增数据权限
     * @param list
     * @return
     */
    int insert(List<RoleDept> list);

    /**
     * 查询数据权限表
     * @param list
     * @return
     */
    List<RoleDept> selectByIpRoleId(List<String> list);

    /**
     * 根据menuId删除角色数据权限关系表
     * @param list
     * @return
     */
    int deleteByMenuId(List<String> list);

    /**
     * 查询角色下数据权限
     * @param id
     * @return
     */
    List<RoleDept> selectByRoleId(Integer id);

}
