package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.MenuDTO;
import com.yn.electricity.request.MenuAlterRequest;
import com.yn.electricity.request.MenuSaveRequest;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: MenuService
 * @Author: zzs
 * @Date: 2021/2/23 14:04
 * @Description: 权限管理
 */
public interface MenuService {

    /**
     * 新增应用
     * @param entity
     * @return
     */
    String insert(MenuSaveRequest entity);

    /**
     * 修改应用 逻辑删除使用修改接口
     * @param entity
     * @return
     */
    String updateById(MenuAlterRequest entity);
    /**
     * 列表查询
     * @param menuDTO
     * @return
     */
    PageInfo selectListPage(MenuDTO menuDTO);

    /**
     * 删除权限
     * @param idList
     * @return
     */
    String deleteById(String idList);

    /**
     * 获取权限树
     * @param code
     * @return
     */
    List<Map<String, Object>> getOperatePermission(String code, String menuType);

}
