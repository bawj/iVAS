package com.yn.electricity.service;

import com.yn.electricity.vo.DeviceLogInfoVO;
import com.yn.electricity.vo.PlatformLogInfoVO;

import java.util.List;

/**
 * @author 平台和设备状态日志
 */
public interface PlatformDeviceLogService {

    /**
     * 查询平台设备日志
     * @param platformId id
     * @param startTime  开始时间
     * @param endTime   结束时间
     * @return list
     */
    List<PlatformLogInfoVO> platformLogInfo(Integer platformId, String startTime, String endTime);

    /**
     * 删除大于30天的日志
     */
    void deletePlatformLogInfo();

    /**
     * 查询设备日志
     * @param devId id
     * @param startTime  开始时间
     * @param endTime   结束时间
     * @return list
     */
    List<DeviceLogInfoVO> deviceLogInfo(Integer devId, String startTime, String endTime);

    /**
     * 删除大于30天的日志
     */
    void deleteDeviceLogInfo();
}
