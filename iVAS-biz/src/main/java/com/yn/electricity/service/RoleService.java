package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.RoleDTO;
import com.yn.electricity.request.RoleAlterRequest;
import com.yn.electricity.request.RoleSaveRequest;
import com.yn.electricity.result.RoleResult;

import java.util.List;

/**
 * @ClassName: RoleService
 * @Author: zzs
 * @Date: 2021/2/23 14:03
 * @Description: 角色管理
 */
public interface RoleService {

    /**
     * 新增角色
     * @param entity
     * @return
     */
    String insert(RoleSaveRequest entity);

    /**
     * 修改角色
     * @param entity
     * @return
     */
    String updateById(RoleAlterRequest entity);

    /**
     * 列表查询 分页
     * @param roleDTO
     * @return
     */
    PageInfo selectPage(RoleDTO roleDTO);

    /**
     * 查询角色下的功能权限
     * @param ipRoleId
     * @return
     */
    List<String> selectByIpRoleId(String ipRoleId);

    /**
     * 查询角色已存在的数据权限
     * @param ipRoleId
     * @return
     */
    List<String> selectByRoleDep(String ipRoleId);

    /**
     * 查询一个用户
     * @param id
     * @return
     */
    RoleResult selectOne(Integer id);

    /**
     * 删除
     * @param idList
     * @return
     */
    String deleteById(List<Integer> idList);
}
