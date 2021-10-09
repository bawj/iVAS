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
    public List<PlatformLogInfoVO> platformLogInfo(Integer platformId) {
        List<PlatformLogInfoVO> platformLogInfoList = platformLogInfoMapper.selectByPlatformId(platformId);
        if (!CollectionUtils.isEmpty(platformLogInfoList)){
            return platformLogInfoList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<DeviceLogInfoVO> deviceLogInfo(Integer devId) {
        List<DeviceLogInfoVO> deviceLogInfoList = deviceLogInfoMapper.selectByDevId(devId);
        if (!CollectionUtils.isEmpty(deviceLogInfoList)){
            return deviceLogInfoList;
        }
        return new ArrayList<>();
    }
}
