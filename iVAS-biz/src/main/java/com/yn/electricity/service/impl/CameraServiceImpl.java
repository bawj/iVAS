package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.dao.ServiceDAO;
import com.yn.electricity.mapper.CameraMapper;
import com.yn.electricity.mapper.DeviceMapper;
import com.yn.electricity.mapper.ServiceMapper;
import com.yn.electricity.result.CameraResult;
import com.yn.electricity.service.CameraService;
import com.yn.electricity.utils.BizParamCheckUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: CameraServiceImpl
 * @Author: zzs
 * @Date: 2021/3/25 16:20
 * @Description:
 */
@Service
public class CameraServiceImpl implements CameraService {
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private ServiceMapper serviceMapper;
    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public List<CameraResult> selectByDevId(Integer devId) {
        if(null == devId){
            BizParamCheckUtils.BizParamCheckException("设备id is not null");
        }

        QueryWrapper<CameraDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dev_id" , devId);

        List<CameraDAO> cameraDAOList = cameraMapper.selectList(queryWrapper);
        List<CameraResult> resultList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(cameraDAOList)){
            DeviceDAO deviceDAO = deviceMapper.selectById(devId);
            ServiceDAO serviceDAO = serviceMapper.selectById(deviceDAO.getServiceId());
            if (serviceDAO != null){
                resultList = cameraDAOList.stream().map(c -> {
                    CameraResult result = new CameraResult();
                    BeanUtils.copyProperties(c, result);

                    result.setIp(serviceDAO.getIp());
                    result.setPort(serviceDAO.getPort());
                    return result;
                }).collect(Collectors.toList());
            }
        }
        return resultList;
    }


    @Override
    public List<CameraDAO> selectByCameraGroupId(Integer cameraGroupId) {
        if(null == cameraGroupId){
            BizParamCheckUtils.BizParamCheckException("cameraGroupId is not null");
        }

        List<CameraDAO> cameraList = cameraMapper.selectByCameraGroupId(cameraGroupId);
        return cameraList;
    }
}
