package com.yn.electricity.service;

import com.yn.electricity.request.DepartmentAlterRequest;
import com.yn.electricity.request.DepartmentSaveRequest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DepartmentService
 * @Author: zzs
 * @Date: 2021/3/11 15:59
 * @Description: 部门管理业务层
 */
public interface DepartmentService {

    /**
     * 新增部门
     * @param request
     * @return
     */
    String insert(DepartmentSaveRequest request);

    /**
     * 修改部门
     * @param request
     * @return
     */
    String updateById(DepartmentAlterRequest request);

    /**
     * 根据部门id删除部门
     * @param idList
     * @return
     */
    String deleteById(List<Integer> idList);

    /**
     * 根据数据权限查询部门
     * @param disabled
     * @return
     */
    List<Map<String, Object>> queryList(String disabled, String available);

}
