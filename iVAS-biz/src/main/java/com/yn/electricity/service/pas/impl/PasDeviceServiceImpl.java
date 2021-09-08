package com.yn.electricity.service.pas.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yn.electricity.ResultVO;
import com.yn.electricity.dao.*;
import com.yn.electricity.enums.DeviceTypeEnum;
import com.yn.electricity.mapper.*;
import com.yn.electricity.pas.*;
import com.yn.electricity.qto.VideoPlanDTO;
import com.yn.electricity.service.pas.PasDeviceService;
import com.yn.electricity.service.pas.PasDeviceServiceFeignClient;
import com.yn.electricity.utils.BizBusinessUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.util.deparser.DeleteDeParser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author admin
 * Date 2021/3/26 15:38
 * Description
 **/
@Slf4j
@Service
public class PasDeviceServiceImpl implements PasDeviceService {

    @Resource
    DeviceTypeMapper deviceTypeMapper;
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private PasDeviceServiceFeignClient  deviceServiceFeignClient;

    @Override
    public Integer sendDelDeviceService(List<Integer> ids) {
        QueryWrapper<DeviceDAO> daoQueryWrapper = new QueryWrapper<>();
        daoQueryWrapper.in("id" , ids);

        List<DeviceDAO> deviceDAOList = deviceMapper.selectList(daoQueryWrapper);

        Map<Integer, List<DeviceDAO>> deviceTypeMap = deviceDAOList.stream().collect(Collectors.groupingBy(DeviceDAO::getDeviceTypeId));
        deviceTypeMap.forEach((deviceTypeId, deviceList) -> {
            DeviceTypeDAO deviceTypeDAO = deviceTypeMapper.selectById(deviceTypeId);
            String type = deviceTypeDAO.getType();
            if (type.equals(DeviceTypeEnum.TYPE_GB28181PLATFORM.getStatus())){
                sendDelPlatformService(deviceList);
            }else {
                sendDelDevice(deviceList);
            }
        });
        return null;
    }

    private void sendDelDevice(List<DeviceDAO> deviceDAOList){
        PasBaseRequest<List<DeleteDeviceRequest>> pasBaseRequest = new PasBaseRequest<>();
        pasBaseRequest.setId(5);
        pasBaseRequest.setMethod("delDevice");
        Map<Integer, List<DeviceDAO>> collect = deviceDAOList.stream().collect(Collectors.groupingBy(DeviceDAO::getServiceId));

        List<DeleteDeviceRequest> requestList = new ArrayList<>();

        collect.forEach((serviceId, deviceList) -> {
            DeleteDeviceRequest deviceRequest = new DeleteDeviceRequest();
            deviceRequest.setServiceId(String.valueOf(serviceId));

            List<DeleteDevice> devices = new ArrayList<>();
            for (DeviceDAO deviceDAO : deviceList) {
                DeleteDevice deleteDevice = new DeleteDevice();
                deleteDevice.setDevId(String.valueOf(deviceDAO.getId()));
                devices.add(deleteDevice);
            }
            deviceRequest.setInfo(devices);
            requestList.add(deviceRequest);
        });

        pasBaseRequest.setParams(requestList);
        log.info("#DeviceServiceImpl.deleteDevice# openFeign 删除设备 {} " , System.currentTimeMillis());
        ResultVO<String> result = deviceServiceFeignClient.delDevice(pasBaseRequest);
        if (result.getCode() != 0){
            log.error("#PasDeviceServiceImpl.sendDelDeviceService# pas删除设备 {}" , result.getMsg());
        }
    }

    public void sendDelPlatformService(List<DeviceDAO> deviceDAOList) {
        PasBaseRequest<List<DeletePlatformRequest>> pasBaseRequest = new PasBaseRequest<>();
        pasBaseRequest.setId(5);
        pasBaseRequest.setMethod("delPlatform");
        Map<Integer, List<DeviceDAO>> collect = deviceDAOList.stream().collect(Collectors.groupingBy(DeviceDAO::getServiceId));

        List<DeletePlatformRequest> requestList = new ArrayList<>();

        collect.forEach((serviceId, deviceList) -> {
            DeletePlatformRequest platformRequest = new DeletePlatformRequest();
            platformRequest.setServiceId(String.valueOf(serviceId));

            List<DeletePlatform> deletePlatformList = new ArrayList<>();
            for (DeviceDAO deviceDAO : deviceList) {
                DeletePlatform deletePlatform = new DeletePlatform();
                deletePlatform.setPlatformId(String.valueOf(deviceDAO.getId()));
                deletePlatformList.add(deletePlatform);
            }
            platformRequest.setInfo(deletePlatformList);
            requestList.add(platformRequest);
        });

        pasBaseRequest.setParams(requestList);
        log.info("#DeviceServiceImpl.sendDelPlatformService# openFeign 删除设备 {} " , System.currentTimeMillis());
        ResultVO<String> result = deviceServiceFeignClient.delPlatform(pasBaseRequest);
        if (result.getCode() != 0){
            log.error("#PasDeviceServiceImpl.sendDelPlatformService# pas删除设备 {}" , result.getMsg());
        }
    }

    @Override
    public Integer sendAddDevicePas(DeviceDAO deviceDAO) {

        PasBaseRequest<List<AddDeviceRequest>> pasBaseRequest = new PasBaseRequest<>();
        pasBaseRequest.setId(4);
        pasBaseRequest.setMethod("addDevice");

        List<AddDeviceRequest> requestList = new ArrayList<>();

        AddDeviceRequest deviceRequest = new AddDeviceRequest();
        deviceRequest.setServiceId(String.valueOf(deviceDAO.getServiceId()));

        DeviceRequest device = new DeviceRequest();
        device.setDevId(String.valueOf(deviceDAO.getId()));
        device.setDeviceGBId(deviceDAO.getCode());
        device.setDevName(deviceDAO.getName());

        List<DeviceRequest> deviceRequests = new ArrayList<>();
        device.setDevType(deviceDAO.getDeviceType());
        device.setUsername(deviceDAO.getRegisterAccount());
        device.setPassword(deviceDAO.getRegisterPassword());
        device.setIp(deviceDAO.getIp());
        device.setPort(deviceDAO.getPort());
        deviceRequests.add(device);
        deviceRequest.setInfo(deviceRequests);

        requestList.add(deviceRequest);

        pasBaseRequest.setParams(requestList);
        log.info("#PasDeviceServiceImpl.sendAddDevicePas# 添加设备 时间 {} " , System.currentTimeMillis());
        ResultVO<String> result = deviceServiceFeignClient.saveEquipment(pasBaseRequest);
        log.info("#PasDeviceServiceImpl.sendAddDevicePas# 添加设备完成 时间 {} result = {}" , System.currentTimeMillis() , result);
        if (result != null && result.getCode() != 0){
            BizBusinessUtils.bizBusinessException(result.getMsg() , result.getData());
        }
        return null;
    }

    @Override
    public Integer sendAddPlatformPas(DeviceDAO deviceDAO) {
        String code = deviceDAO.getCode();
        if (StringUtils.isEmpty(code)){
            BizBusinessUtils.bizBusinessException("国标编码不能为空");
        }

        PasBaseRequest<PasInfo<PasAddPlatformVO>> pasParamVO = new PasBaseRequest<>();
        pasParamVO.setId(7);
        pasParamVO.setMethod("addPlatform");

        PasInfo<PasAddPlatformVO> pasInfo = new PasInfo<>();
        List<PasAddPlatformVO> pasAddPlatformVOList = new ArrayList<>();

        PasAddPlatformVO pasAddPlatformVO = new PasAddPlatformVO();
        pasAddPlatformVO.setPlatformId(String.valueOf(deviceDAO.getId()));
        pasAddPlatformVO.setPlatformName(deviceDAO.getName());
        pasAddPlatformVO.setPlatformType(deviceDAO.getDeviceType());
        pasAddPlatformVO.setIp(deviceDAO.getIp());
        pasAddPlatformVO.setPort(deviceDAO.getPort());
        pasAddPlatformVO.setUsername(deviceDAO.getRegisterAccount());
        pasAddPlatformVO.setPassword(deviceDAO.getRegisterPassword());
        pasAddPlatformVO.setVendor("");
        pasAddPlatformVO.setServiceId(deviceDAO.getServiceId());
        pasAddPlatformVO.setDomainId(deviceDAO.getCode());

        pasAddPlatformVOList.add(pasAddPlatformVO);
        pasInfo.setInfo(pasAddPlatformVOList);
        pasParamVO.setParams(pasInfo);
        log.info("#PasDeviceServiceImpl.sendAddPlatformPas# 添加设备 时间 {} " , System.currentTimeMillis());
        ResultVO<String> result = deviceServiceFeignClient.addPlatform(pasParamVO);
        log.info("#PasDeviceServiceImpl.sendAddPlatformPas# 添加设备完成 时间 {} result = {}" , System.currentTimeMillis() , result);
        if (result != null && result.getCode() != 0){
            BizBusinessUtils.bizBusinessException(result.getMsg() , result.getData());
        }
        return null;
    }

    @Override
    public void sendVideoPlan(VideoPlanDTO videoPlanDTO) {
        PasBaseRequest<PasVideoPlanInfo> pasBaseRequest = new PasBaseRequest<>();
        pasBaseRequest.setId(1);
        pasBaseRequest.setMethod("recordPlan");
        PasVideoPlanInfo pasVideoPlanInfo = new PasVideoPlanInfo();
        pasVideoPlanInfo.setDevId(String.valueOf(videoPlanDTO.getDevId()));
        pasVideoPlanInfo.setChannelNo(videoPlanDTO.getChannelNo());
        pasVideoPlanInfo.setIp(videoPlanDTO.getIp());
        pasVideoPlanInfo.setPort(videoPlanDTO.getPort());
        List<VideoPlanDAO> videoPlanDAOList = videoPlanDTO.getVideoPlanDAOList();
        List<PasVideoPlanInfo.InfoDTO> info = new ArrayList<>();
        for (VideoPlanDAO videoPlanDAO : videoPlanDAOList) {
            PasVideoPlanInfo.InfoDTO infoDTO = new PasVideoPlanInfo.InfoDTO();
            infoDTO.setPlanId(String.valueOf(videoPlanDAO.getId()));
            infoDTO.setPlanType(infoDTO.getPlanType());
            infoDTO.setSubType(infoDTO.getSubType());
            infoDTO.setBeginTime(infoDTO.getBeginTime());
            infoDTO.setEndTime(infoDTO.getEndTime());
            info.add(infoDTO);
        }
        pasVideoPlanInfo.setInfo(info);
        pasBaseRequest.setParams(pasVideoPlanInfo);
        ResultVO<String> result = deviceServiceFeignClient.sendVideoPlan(pasBaseRequest);
        log.info("#PasDeviceServiceImpl.pasBaseRequest# 添加录像计划 参数 {} " , pasBaseRequest.toString());
        if (result != null && result.getCode() != 0){
            BizBusinessUtils.bizBusinessException(result.getMsg() , result.getData());
        }
    }

    @Override
    public void sendDelVideoPlan(ServiceDAO serviceDAO,List<Integer> ids) {
        //删除录像计划
        PasBaseRequest<PasDelVideoPlanInfo> pasBaseRequest = new PasBaseRequest<>();
        pasBaseRequest.setId(1);
        pasBaseRequest.setMethod("delPlan");
        PasDelVideoPlanInfo pasDelVideoPlanInfo = new PasDelVideoPlanInfo();
        pasDelVideoPlanInfo.setIp(serviceDAO.getIp());
        pasDelVideoPlanInfo.setPort(serviceDAO.getPort());
        pasDelVideoPlanInfo.setPlanId(ids);
        pasBaseRequest.setParams(pasDelVideoPlanInfo);
        ResultVO<String> result = deviceServiceFeignClient.sendDelVideoPlan(pasBaseRequest);
        log.info("#PasDeviceServiceImpl.pasBaseRequest# 删除录像计划 参数 {} " , pasBaseRequest.toString());
        if (result != null && result.getCode() != 0){
            BizBusinessUtils.bizBusinessException(result.getMsg() , result.getData());
        }
    }


}
