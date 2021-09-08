package com.yn.electricity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.dao.DeviceTypeDAO;
import com.yn.electricity.dao.PlatformDAO;
import com.yn.electricity.mapper.DeviceTypeMapper;
import com.yn.electricity.mapper.PlatformMapper;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.PlatformQueryDTO;
import com.yn.electricity.request.PlatformAlterRequest;
import com.yn.electricity.request.PlatformSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.DevicePlatformUtilService;
import com.yn.electricity.service.PlatformService;
import com.yn.electricity.service.pas.PasPlatformService;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.EntityUtil;
import com.yn.electricity.util.PWDUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.ListUtil;
import com.yn.electricity.vo.PlatformVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author admin
 * Date 2021/9/3 14:07
 * Description
 **/
@Service
public class PlatformServiceImpl implements PlatformService {

    @Resource
    private DataPermissionUtil dataPermissionUtil;
    @Resource
    private PasPlatformService pasPlatformService;
    @Resource
    private DevicePlatformUtilService devicePlatformUtilService;
    @Resource
    private PlatformMapper platformMapper;
    @Resource
    private DeviceTypeMapper deviceTypeMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;


    @Override
    public String addPlatform(PlatformSaveRequest platformSaveRequest) {
        String name = platformSaveRequest.getName();

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("name", name);
        List<PlatformDAO> platformDAOList = platformMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(platformDAOList)) {
            BizBusinessUtils.bizBusinessException("设备名称重复");
        }

        String code = platformSaveRequest.getCode();
        HashMap<String, Object> codeMap = Maps.newHashMap();
        codeMap.put("code", code);
        platformDAOList = platformMapper.selectByMap(codeMap);
        if (!CollectionUtils.isEmpty(platformDAOList)) {
            BizBusinessUtils.bizBusinessException("国标编码重复");
        }

        Integer serviceId = platformSaveRequest.getServiceId();

        Integer deviceTypeId = platformSaveRequest.getDeviceTypeId();
        DeviceTypeDAO deviceTypeDAO = deviceTypeMapper.selectById(deviceTypeId);
        String type = deviceTypeDAO.getType();
        //查询传入的type是否和serviceId一致
        devicePlatformUtilService.serviceType(serviceId, type);

        PlatformDAO platformDAO = new PlatformDAO();
        platformDAO.setName(platformSaveRequest.getName());
        platformDAO.setDeviceTypeId(platformSaveRequest.getDeviceTypeId());
        platformDAO.setServiceId(platformSaveRequest.getServiceId());
        platformDAO.setOrganizationId(platformSaveRequest.getOrganizationId());
        platformDAO.setIp(platformSaveRequest.getIp());
        platformDAO.setPort(platformSaveRequest.getPort());
        platformDAO.setRegisterAccount(platformSaveRequest.getRegisterAccount());
        platformDAO.setCode(platformSaveRequest.getCode());
        PWDUtil pwdUtil = new PWDUtil();
        platformDAO.setRegisterPassword(pwdUtil.encryptPassword(platformSaveRequest.getRegisterPassword()));
        platformDAO.setOnline((short) 0);

        UserResult userResult = redisInfoUtil.getUserResult();
        EntityUtil.setCpyCodeDepCodeIpIdUserId(platformDAO, userResult);
        platformDAO.setCreateTime(new Date());
        platformDAO.setUpdateTime(new Date());

        platformMapper.insert(platformDAO);

        List<PlatformDAO> platformList = new ArrayList<>();
        platformList.add(platformDAO);
        //添加设备到cms服务
        pasPlatformService.sendAddPlatformPas(platformList);
        return null;
    }

    @Override
    public String alterPlatform(PlatformAlterRequest platformAlterRequest) {
        Integer id = platformAlterRequest.getId();

        PlatformDAO platformDAO = platformMapper.selectById(id);
        if (platformDAO == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }

        String name = platformAlterRequest.getName();
        PlatformDAO platform = platformMapper.selectByNameNotId(name, id);
        if (platform != null) {
            BizBusinessUtils.bizBusinessException("设备名称重复");
        }
        //统一编码不能重复
        String code = platformAlterRequest.getCode();
        List<DeviceDAO> deviceDAOList = platformMapper.selectByCodeNotId(code, id);
        if (!CollectionUtils.isEmpty(deviceDAOList)) {
            BizBusinessUtils.bizBusinessException("国标编码重复");
        }

        BeanUtils.copyProperties(platformAlterRequest, platformDAO);
        ///密码加密
        PWDUtil pwdUtil = new PWDUtil();
        platformDAO.setRegisterPassword(pwdUtil.encryptPassword(platformDAO.getRegisterPassword()));
        UserResult userResult = redisInfoUtil.getUserResult();
        platformDAO.setUpdateUserId(userResult.getId());

        platformMapper.updateById(platformDAO);

        //向cms发送修改设备的請求
        //先删除 在添加
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        pasPlatformService.sendDelPlatformService(ids);

        List<PlatformDAO> platformDAOList = new ArrayList<>();
        platformDAOList.add(platformDAO);
        pasPlatformService.sendAddPlatformPas(platformDAOList);

        return null;
    }


    @Override
    public String deletePlatform(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        //向cms发送删除设备的請求 pas必须先删除
        pasPlatformService.sendDelPlatformService(ids);
        //删除设备
        platformMapper.deleteBatchIds(ids);
        return null;
    }

    @Override
    public PageInfo<PlatformVO> findPlatform(PlatformQueryDTO deviceQuery) {
        int pageNum = deviceQuery.getPageNum() == null ? 1 : deviceQuery.getPageNum();
        int pageSize = deviceQuery.getPageSize() == null ? 30 : deviceQuery.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();

        List<PlatformVO> deviceList = platformMapper.selectPlatform(deviceQuery, dataPermission);
        PWDUtil pwdUtil = new PWDUtil();
        for (PlatformVO platform : deviceList) {
            String registerPassword = platform.getRegisterPassword();
            platform.setRegisterPassword(pwdUtil.decryptPassword(registerPassword));
        }
        return new PageInfo<>(ListUtil.newArrayList(deviceList));
    }

}
