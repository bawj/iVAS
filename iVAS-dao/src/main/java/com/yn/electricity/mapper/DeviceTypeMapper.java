package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DeviceTypeDAO;

import java.util.List;

/**
 * @author 设备类型相关查询
 */
public interface DeviceTypeMapper extends BaseMapper<DeviceTypeDAO> {


    /**
     * 查询设备类型
     * @return device
     */
    List<DeviceTypeDAO> selectByDeviceType();

    /**
     * 查询平台类型
     * @return List
     */
    List<DeviceTypeDAO> selectByPlatformType();


}