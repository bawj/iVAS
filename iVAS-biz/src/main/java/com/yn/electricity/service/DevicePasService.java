package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.vo.ServiceVO;

/**
 * @author 接入服务管理
 */
public interface DevicePasService {


    /**
     * 查询服务
     * @param pageNum 分页
     * @param pageSize 分页
     * @param deviceTypeId deviceTypeId
     * @return pageInfo
     */
    PageInfo<ServiceVO> findService(Integer pageNum, Integer pageSize,Integer deviceTypeId);
}
