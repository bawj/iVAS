package com.yn.electricity.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.*;
import com.yn.electricity.enums.DeviceTypeEnum;
import com.yn.electricity.enums.ServiceEnum;
import com.yn.electricity.mapper.*;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.DeviceQueryDTO;
import com.yn.electricity.request.DeviceAlterRequest;
import com.yn.electricity.request.DeviceExcelRequest;
import com.yn.electricity.request.DeviceSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.DevicePlatformUtilService;
import com.yn.electricity.service.DeviceService;
import com.yn.electricity.service.RedisInfoService;
import com.yn.electricity.service.pas.PasDeviceService;
import com.yn.electricity.util.*;
import com.yn.electricity.util.excel.DeviceExcelDataListener;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.ListUtil;
import com.yn.electricity.vo.DeviceResultVO;
import com.yn.electricity.vo.DeviceVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * Date 2021/3/17 10:45
 * Description 设备相关
 **/
@Slf4j
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper , DeviceDAO> implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private PasDeviceService pasDeviceService;
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private ServiceMapper serviceMapper;
    @Resource
    private DeviceTypeMapper deviceTypeMapper;
    @Resource
    private RedisInfoService redisInfoService;
    @Resource
    private DataPermissionUtil dataPermissionUtil;
    @Resource
    private CameraGroupCameraMapper cameraGroupCameraMapper;
    @Resource
    private AlarmInfoMapper alarmInfoMapper;
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private DevicePlatformUtilService devicePlatformUtilService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addDevice(DeviceSaveRequest deviceSaveRequest) {
        String name = deviceSaveRequest.getName();

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("name" , name);
        List<DeviceDAO> devices = deviceMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(devices)){
            BizBusinessUtils.bizBusinessException("设备名称重复");
        }
        //统一编码不能重复
        String code = deviceSaveRequest.getCode();
        if (!StringUtils.isEmpty(code)){
            HashMap<String, Object> codeMap = Maps.newHashMap();
            codeMap.put("code" , code);
            List<DeviceDAO> deviceDAOList = deviceMapper.selectByMap(codeMap);
            if (!CollectionUtils.isEmpty(deviceDAOList)){
                BizBusinessUtils.bizBusinessException("国标编码重复");
            }
        }
        Integer deviceTypeId = deviceSaveRequest.getDeviceTypeId();
        DeviceTypeDAO deviceTypeDAO = deviceTypeMapper.selectById(deviceTypeId);

        DeviceDAO deviceDAO = new DeviceDAO();
        BeanUtils.copyProperties(deviceSaveRequest , deviceDAO);

        ///密码加密
        PWDUtil pwdUtil = new PWDUtil();
        deviceDAO.setRegisterPassword(pwdUtil.encryptPassword(deviceDAO.getRegisterPassword()));

        UserResult userResult = redisInfoUtil.getUserResult();
        EntityUtil.setCpyCodeDepCodeIpIdUserId(deviceDAO , userResult);
        Integer organizationId = deviceSaveRequest.getOrganizationId();
        OrganizationDAO organizationDAO = organizationMapper.selectById(organizationId);
        deviceDAO.setDepartmentCode(organizationDAO.getDepartmentCode());
        deviceDAO.setCreateTime(new Date());
        deviceDAO.setUpdateTime(new Date());
        deviceDAO.setDeviceType(deviceTypeDAO.getType());
        deviceDAO.setOnline(0);
        deviceMapper.insert(deviceDAO);

        Integer serviceId = deviceSaveRequest.getServiceId();
        String type = deviceTypeDAO.getType();
        //查询传入的type是否和serviceId一致
        devicePlatformUtilService.serviceType(serviceId, type);

        //添加设备到cms服务
        pasDeviceService.sendAddDevicePas(deviceDAO);

        /*if (type.equals(DeviceTypeEnum.TYPE_GB28181PLATFORM.getStatus())){
            //添加设备到cms服务
            pasDeviceService.sendAddPlatformPas(deviceDAO);
        }else {
            //添加设备到cms服务
            pasDeviceService.sendAddDevicePas(deviceDAO);
        }*/
        return null;
    }


    private void pasIsOnline(Integer serviceId , String deviceType){
        HashMap<String, Object> serviceMap = Maps.newHashMap();
        serviceMap.put("id" , serviceId);
        serviceMap.put("vendor" , deviceType);
        List<ServiceDAO> serviceList = serviceMapper.selectByMap(serviceMap);
        if (CollectionUtils.isEmpty(serviceList)){
            BizBusinessUtils.bizBusinessException("serviceId 错误 或 设备类型与接入服务类型不一致");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String alterDevice(DeviceAlterRequest deviceAlterRequest) {
        Integer id = deviceAlterRequest.getId();
        String name = deviceAlterRequest.getName();

        DeviceDAO deviceDAO = deviceMapper.selectById(id);
        if (deviceDAO == null){
            BizBusinessUtils.bizBusinessException("id 错误");
        }

        Integer serviceId = deviceAlterRequest.getServiceId();
        ServiceDAO service = serviceMapper.selectById(serviceId);
        if (service == null){
            BizBusinessUtils.bizBusinessException("serviceId 错误");
        }
        Short online = service.getOnline();
        if ((short)online == ServiceEnum.N.getStatus()){
            BizBusinessUtils.bizBusinessException("接入服务不在线");
        }

        DeviceDAO device = deviceMapper.selectByNameNotId(name , id);
        if (device != null){
            BizBusinessUtils.bizBusinessException("设备名称重复");
        }

        //统一编码不能重复
        String code = deviceAlterRequest.getCode();
        if (!StringUtils.isEmpty(code)){
            List<DeviceDAO> deviceDAOList = deviceMapper.selectByCodeNotId(code , id);
            if (!CollectionUtils.isEmpty(deviceDAOList)){
                BizBusinessUtils.bizBusinessException("国标编码重复");
            }
        }

        //修改镜头的经纬度
        updateCameraLatLng(deviceAlterRequest);

        BeanUtils.copyProperties(deviceAlterRequest , deviceDAO);
        deviceDAO.setPort(Integer.parseInt(deviceAlterRequest.getPort()));

        ///密码加密
        PWDUtil pwdUtil = new PWDUtil();
        deviceDAO.setRegisterPassword(pwdUtil.encryptPassword(deviceDAO.getRegisterPassword()));

        UserResult userResult = redisInfoUtil.getUserResult();
        deviceDAO.setUpdateUserId(userResult.getId());

        deviceMapper.updateById(deviceDAO);

        Integer deviceTypeId = deviceAlterRequest.getDeviceTypeId();
        DeviceTypeDAO deviceTypeDAO = deviceTypeMapper.selectById(deviceTypeId);
        String type = deviceTypeDAO.getType();
        //查询传入的type是否和serviceId一致
        devicePlatformUtilService.serviceType(serviceId, type);

        deviceDAO.setDeviceType(type);
        //向cms发送修改设备的請求
        //先删除 在添加
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        pasDeviceService.sendDelDeviceService(ids);
        //添加设备到cms服务
        pasDeviceService.sendAddDevicePas(deviceDAO);

        /*if (type.equals(DeviceTypeEnum.TYPE_GB28181PLATFORM.getStatus())){
            //添加设备到cms服务
            pasDeviceService.sendAddPlatformPas(deviceDAO);
        }else {
            //添加设备到cms服务
            pasDeviceService.sendAddDevicePas(deviceDAO);
        }*/

        //跟新redis缓存
        redisInfoService.redisUpdatePreloadingDevice(id);
        return null;
    }

    private void updateCameraLatLng(DeviceAlterRequest deviceAlterRequest) {
        String latitude = deviceAlterRequest.getLatitude();
        String longitude = deviceAlterRequest.getLongitude();
        Integer id = deviceAlterRequest.getId();

        QueryWrapper<CameraDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dev_id" , id);
        List<CameraDAO> cameraDAOList = cameraMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(cameraDAOList)){
            for (CameraDAO cameraDAO : cameraDAOList) {
                cameraDAO.setLatitude(DensityUtils.stringTypeDouble(latitude));
                cameraDAO.setLongitude(DensityUtils.stringTypeDouble(longitude));
                cameraMapper.updateById(cameraDAO);
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteDevice(List<Integer> ids) {
        log.info("#DeviceServiceImpl.deleteDevice# 删除设备 {} " , System.currentTimeMillis());
        if (CollectionUtils.isEmpty(ids)){
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        //向cms发送删除设备的請求 pas必须先删除
        pasDeviceService.sendDelDeviceService(ids);
        log.info("#DeviceServiceImpl.deleteDevice# pas 删除设备完成 {} " , System.currentTimeMillis());
        //删除设备
        deviceMapper.deleteBatchIds(ids);

        QueryWrapper<CameraDAO> cameraQueryWrapper = new QueryWrapper<>();
        cameraQueryWrapper.in("dev_id" , ids);
        //删除相关联的镜头组
        List<CameraDAO> cameraList = cameraMapper.selectList(cameraQueryWrapper);
        if (!CollectionUtils.isEmpty(cameraList)){
            List<Integer> cameraIdList= cameraList.stream().map(CameraDAO::getId).collect(Collectors.toList());
            //删除镜头的报警信息
            QueryWrapper<AlarmInfoDAO> alarmInfoQueryWrapper = new QueryWrapper<>();
            alarmInfoQueryWrapper.in("camera_id" , cameraIdList);
            alarmInfoMapper.delete(alarmInfoQueryWrapper);

            //删除镜头组关联的镜头信息
            QueryWrapper<CameraGroupCameraDAO> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("camera_id" , cameraIdList);
            cameraGroupCameraMapper.delete(queryWrapper);

            //删除设备下的所有camera信息
            cameraMapper.deleteBatchIds(cameraIdList);
        }

        //删除设备的缓存信息
        for (Integer id : ids) {
            redisInfoService.delPreloadIngDevice(id);
        }
        return null;
    }
    @Override
    public List<DeviceVO> findDevice(DeviceQueryDTO deviceQuery) {
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
        List<DeviceVO> deviceList = deviceMapper.selectDevice(deviceQuery,dataPermission);
        PWDUtil pwdUtil = new PWDUtil();
        for (DeviceVO deviceVO : deviceList) {
            String registerPassword = deviceVO.getRegisterPassword();
            deviceVO.setRegisterPassword(pwdUtil.decryptPassword(registerPassword));
        }
        return ListUtil.newArrayList(deviceList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importDevice(MultipartFile excelFile) {
        if (excelFile == null){
            BizBusinessUtils.bizBusinessException("读取excel错误");
        }
        String filename = excelFile.getResource().getFilename();
        log.info("#DeviceServiceImpl.importDevice# 导入excel文件名 name = {} " , filename);
        if (StringUtils.isEmpty(filename)){
            BizBusinessUtils.bizBusinessException("读取excel错误 文件错误");
        }
        if (!filename.contains(".")){
            BizBusinessUtils.bizBusinessException("读取excel错误 文件错误");
        }
        String[] split = filename.split("\\.");
        String fileNameSuffix = split[1];
        if (!fileNameSuffix.equals(StringContent.EXCEL_XLS) && !fileNameSuffix.equals(StringContent.EXCEL_XLSX)){
            BizBusinessUtils.bizBusinessException("导入文件不是excel");
        }
        try {
            InputStream inputStream = excelFile.getInputStream();
            EasyExcel.read(inputStream, DeviceExcelRequest.class, new DeviceExcelDataListener()).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            BizBusinessUtils.bizBusinessException("读取excel错误 文件错误");
        }
        return null;
    }


    @Override
    public List<DeviceResultVO> selectByCameraGroupId(Integer id) {
        BaseQuery query = dataPermissionUtil.getDataPermission();
        return deviceMapper.selectByCameraGroupId(query, id);
    }

}
