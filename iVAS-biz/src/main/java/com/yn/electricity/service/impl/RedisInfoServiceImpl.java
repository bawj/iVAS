package com.yn.electricity.service.impl;

import com.yn.electricity.mapper.DeviceMapper;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.DeviceDTO;
import com.yn.electricity.service.RedisInfoService;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.RedisInfoUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * Date 2021/6/1 17:21
 * Description
 **/
@Service
public class RedisInfoServiceImpl implements RedisInfoService {

    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public void redisPreloadingDevice() {
        redisInfoUtil.delDevice();
        List<DeviceDTO> deviceList = deviceMapper.selectDeviceAndCamera();
        if (!CollectionUtils.isEmpty(deviceList)){
            redisInfoUtil.setDevice(deviceList);
        }
    }

    /**
     * 对单个device包括设备下的镜头信息缓存
     */
    @Override
    public void redisUpdatePreloadingDevice(Integer devId) {
        DeviceDTO deviceDTO = deviceMapper.findDeviceAndCameraByDeviceId(devId);
        List<DeviceDTO> deviceList = redisInfoUtil.getDeviceList();
        if (!CollectionUtils.isEmpty(deviceList)){
            Optional<DeviceDTO> deviceOptional = deviceList.stream()
                    .filter(device -> device.getId().equals(devId)).findFirst();
            if (deviceOptional.isPresent()){
                DeviceDTO device = deviceOptional.get();
                deviceList.remove(device);
                deviceList.add(deviceDTO);
                redisInfoUtil.setDevice(deviceList);
            }
        }
    }

    @Override
    public void delPreloadIngDevice(Integer devId) {
        List<DeviceDTO> deviceList = redisInfoUtil.getDeviceList();
        if (!CollectionUtils.isEmpty(deviceList)){
            Optional<DeviceDTO> deviceOptional = deviceList.stream()
                    .filter(device -> device.getId().equals(devId)).findFirst();
            if (deviceOptional.isPresent()){
                DeviceDTO device = deviceOptional.get();
                deviceList.remove(device);
                redisInfoUtil.setDevice(deviceList);
            }
        }
    }
}
