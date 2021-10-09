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
     * @return list
     */
    List<PlatformLogInfoVO> platformLogInfo(Integer platformId);

    /**
     * 查询设备日志
     * @param devId id
     * @return list
     */
    List<DeviceLogInfoVO> deviceLogInfo(Integer devId);
}
