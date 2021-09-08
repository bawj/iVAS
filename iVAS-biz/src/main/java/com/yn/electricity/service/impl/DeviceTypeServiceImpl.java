package com.yn.electricity.service.impl;

import com.yn.electricity.dao.DeviceTypeDAO;
import com.yn.electricity.mapper.DeviceTypeMapper;
import com.yn.electricity.service.DeviceTypeService;
import com.yn.electricity.vo.DeviceTypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * Date 2021/3/16 17:33
 * Description
 **/
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Resource
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public List<DeviceTypeVO> findDeviceType() {
        List<DeviceTypeVO> deviceTypes = new ArrayList<>();

        List<DeviceTypeDAO> deviceTypeList = deviceTypeMapper.selectList(null);
        deviceTypeList.forEach(deviceTypeDAO -> {
            DeviceTypeVO deviceType = new DeviceTypeVO();
            BeanUtils.copyProperties(deviceTypeDAO , deviceType);
            deviceTypes.add(deviceType);
        });

        return deviceTypes;
    }
}
