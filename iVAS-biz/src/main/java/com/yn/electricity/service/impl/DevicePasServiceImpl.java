package com.yn.electricity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.dao.DeviceTypeDAO;
import com.yn.electricity.dao.ServiceDAO;
import com.yn.electricity.mapper.DeviceTypeMapper;
import com.yn.electricity.mapper.ServiceMapper;
import com.yn.electricity.service.DevicePasService;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.ListUtil;
import com.yn.electricity.vo.ServiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * Date 2021/3/16 15:25
 * Description 接入服务管理
 **/
@Service
public class DevicePasServiceImpl implements DevicePasService {

    @Resource
    private ServiceMapper serviceMapper;
    @Resource
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public PageInfo<ServiceVO> findService(Integer pageNum, Integer pageSize,Integer deviceTypeId) {
        String deviceType = "";
        if (deviceTypeId != null){
            DeviceTypeDAO deviceTypeDAO = deviceTypeMapper.selectById(deviceTypeId);
            if (deviceTypeDAO == null){
                BizBusinessUtils.bizBusinessException("deviceTypeId 错误");
            }
            deviceType = deviceTypeDAO.getType();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ServiceVO> serviceList = new ArrayList<>();
        List<ServiceDAO> serviceDAOList = serviceMapper.selectByOnlineAndVendor(deviceType);

        serviceDAOList.forEach(serviceDAO -> {
            ServiceVO service = new ServiceVO();
            BeanUtils.copyProperties(serviceDAO, service);
            serviceList.add(service);
        });

        return new PageInfo<>(ListUtil.newArrayList(serviceList));
    }




}
