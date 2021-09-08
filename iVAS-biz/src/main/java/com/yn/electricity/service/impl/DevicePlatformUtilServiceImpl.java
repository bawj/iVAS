package com.yn.electricity.service.impl;

import com.google.common.collect.Maps;
import com.yn.electricity.dao.ServiceDAO;
import com.yn.electricity.mapper.ServiceMapper;
import com.yn.electricity.service.DevicePlatformUtilService;
import com.yn.electricity.utils.BizBusinessUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author admin
 * Date 2021/9/3 14:11
 * Description
 **/
@Service
public class DevicePlatformUtilServiceImpl implements DevicePlatformUtilService {

    @Resource
    private ServiceMapper serviceMapper;

    @Override
    public void serviceType(Integer serviceId , String type){
        HashMap<String, Object> serviceMap = Maps.newHashMap();
        serviceMap.put("id" , serviceId);
        serviceMap.put("vendor" , type);
        List<ServiceDAO> serviceList = serviceMapper.selectByMap(serviceMap);
        if (CollectionUtils.isEmpty(serviceList)){
            BizBusinessUtils.bizBusinessException("serviceId 错误 或 设备类型与接入服务类型不一致");
        }
    }

}
