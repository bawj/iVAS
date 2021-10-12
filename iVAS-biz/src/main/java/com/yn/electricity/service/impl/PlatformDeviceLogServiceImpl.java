package com.yn.electricity.service.impl;

import com.yn.electricity.mapper.DeviceLogInfoMapper;
import com.yn.electricity.mapper.PlatformLogInfoMapper;
import com.yn.electricity.service.PlatformDeviceLogService;
import com.yn.electricity.vo.DeviceLogInfoVO;
import com.yn.electricity.vo.PlatformLogInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * Date 2021/10/9 14:37
 * Description
 **/
@Service
public class PlatformDeviceLogServiceImpl implements PlatformDeviceLogService {

    @Resource
    private PlatformLogInfoMapper platformLogInfoMapper;
    @Resource
    private DeviceLogInfoMapper deviceLogInfoMapper;

    @Override
    public List<PlatformLogInfoVO> platformLogInfo(Integer platformId, String startTime, String endTime) {
        List<PlatformLogInfoVO> platformLogInfoList = platformLogInfoMapper.selectByPlatformId(platformId,startTime,endTime);
        if (!CollectionUtils.isEmpty(platformLogInfoList)){
            return platformLogInfoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deletePlatformLogInfo() {
        platformLogInfoMapper.deleteTime();
    }

    @Override
    public List<DeviceLogInfoVO> deviceLogInfo(Integer devId, String startTime, String endTime) {
        List<DeviceLogInfoVO> deviceLogInfoList = deviceLogInfoMapper.selectByDevId(devId,startTime,endTime);
        if (!CollectionUtils.isEmpty(deviceLogInfoList)){
            return deviceLogInfoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteDeviceLogInfo() {
        deviceLogInfoMapper.deleteTime();
    }
}
