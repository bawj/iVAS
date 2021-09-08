package com.yn.electricity.service;

import com.yn.electricity.request.CompanyAlterRequest;
import com.yn.electricity.request.CompanySaveRequest;

/**
 * @ClassName: CompanyService
 * @Author: zzs
 * @Date: 2021/3/11 16:02
 * @Description: 企业信息管理业务层
 */
public interface CompanyService {
    /**
     * 新增企业信息
     * @param request
     * @return
     */
    String insert(CompanySaveRequest request);

    /**
     * 修改企业信息
     * @param request
     * @return
     */
    String updateById(CompanyAlterRequest request);

}
