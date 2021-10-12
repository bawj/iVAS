package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.vo.ServiceLogInfoVO;
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

    /**
     * 查询服务日志
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @param serviceId serviceId
     * @param startTime 时间
     * @param endTime 时间
     * @return PageInfo
     */
    PageInfo<ServiceLogInfoVO> findServiceLogInfo(Integer pageNum, Integer pageSize, Integer serviceId, String startTime, String endTime);

    /**
     * 删除大于30的日志
     */
    void deleteServiceLogInfo();
}
