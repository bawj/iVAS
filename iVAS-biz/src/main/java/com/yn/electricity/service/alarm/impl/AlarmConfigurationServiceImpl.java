package com.yn.electricity.service.alarm.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.*;
import com.yn.electricity.entity.DistributionTimeEntity;
import com.yn.electricity.mapper.*;
import com.yn.electricity.qto.AlarmConfigurationDTO;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.ConfigurationDTO;
import com.yn.electricity.qto.DeviceDTO;
import com.yn.electricity.request.AlarmConfigurationAlterRequest;
import com.yn.electricity.request.AlarmConfigurationRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.alarm.AlarmConfigurationService;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.EntityUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.vo.AlarmConfigurationOneVO;
import com.yn.electricity.vo.AlarmConfigurationVO;
import com.yn.electricity.vo.ConfigurationTypeVO;
import com.yn.electricity.vo.ResponseTypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * Date 2021/5/21 9:36
 * Description 报警配置service
 **/
@Service
public class AlarmConfigurationServiceImpl implements AlarmConfigurationService {

    @Resource
    private DataPermissionUtil dataPermissionUtil;
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private ArtAcMapper artAcMapper;
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private AlarmConfigurationTypeMapper configurationTypeMapper;
    @Resource
    private AlarmResponseTypeMapper responseTypeMapper;
    @Resource
    private AlarmConfigurationMapper configurationMapper;
    @Resource
    private AcOrganizationMapper acOrganizationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addConfiguration(AlarmConfigurationRequest configurationRequest) {
        String name = configurationRequest.getName();
        HashMap<String, Object> configurationMap = Maps.newHashMap();
        configurationMap.put("name", name);
        List<AlarmConfigurationDAO> alarmConfigurationList = configurationMapper.selectByMap(configurationMap);
        if (!CollectionUtils.isEmpty(alarmConfigurationList)) {
            BizBusinessUtils.bizBusinessException("配置名称不能重复");
        }
        //组织机构id
        List<Integer> organizationIds = configurationRequest.getOrganizationIds();
        //判断布防时间是否符合规则
        DistributionTimeEntity distributionTime = configurationRequest.getDistributionTime();
        //响应类型是否正确
        List<Integer> responseTypeIds = configurationRequest.getResponseTypeIds();
        Integer configurationTypeId = configurationRequest.getConfigurationTypeId();
        //判断资源是否有误
        execResources(organizationIds, distributionTime, responseTypeIds, configurationTypeId);
        //判断关联的组织机构是否有重复关联配置类型
        List<Integer> organizationLists = acOrganizationMapper.repeatOrganizationIds(null,configurationTypeId, organizationIds);
        if (!CollectionUtils.isEmpty(organizationLists)) {
            //organizationIds.removeAll(organizationLists);
            Integer organizationId = organizationLists.get(0);
            OrganizationDAO organizationDAO = organizationMapper.selectById(organizationId);
            BizBusinessUtils.bizBusinessException("{} 重复添加报警配置" , organizationDAO.getName());
        }
        if (!CollectionUtils.isEmpty(organizationIds)) {
            //报警等级
            Integer alarmGrade = configurationRequest.getAlarmGrade();
            String remarks = configurationRequest.getRemarks();
            Boolean enable = configurationRequest.getEnable();
            //报存到数据库
            AlarmConfigurationDAO configuration = new AlarmConfigurationDAO();
            configuration.setName(configurationRequest.getName());
            configuration.setAlarmConfigurationTypeId(configurationRequest.getConfigurationTypeId());
            configuration.setDistributionTime(JSON.toJSONString(distributionTime));
            configuration.setAlarmGrade(alarmGrade);
            configuration.setRemarks(remarks);
            configuration.setEnable(enable);
            configuration.setCreateTime(new Date());
            configuration.setUpdateTime(new Date());

            UserResult userResult = redisInfoUtil.getUserResult();
            EntityUtil.setCpyCodeDepCodeIpIdUserId(configuration, userResult);
            configurationMapper.insert(configuration);

            Integer id = configuration.getId();
            //资源关联
            saveAcOrganizationCameraGroup(id, organizationIds);
            //关联响应类型
            saveArtAc(id, responseTypeIds);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateConfiguration(AlarmConfigurationAlterRequest configurationAlterRequest) {
        Integer id = configurationAlterRequest.getId();
        AlarmConfigurationDAO configurationDAO = configurationMapper.selectById(id);
        if (configurationDAO == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        //判断布防时间是否符合规则
        DistributionTimeEntity distributionTime = configurationAlterRequest.getDistributionTime();
        //响应类型是否正确
        List<Integer> responseTypeIds = configurationAlterRequest.getResponseTypeIds();
        Integer configurationTypeId = configurationAlterRequest.getConfigurationTypeId();
        List<Integer> organizationIds = configurationAlterRequest.getOrganizationIds();
        //判断资源是否有误
        execResources(organizationIds, distributionTime, responseTypeIds, configurationTypeId);
        //判断关联的组织机构是否有重复关联配置类型
        List<Integer> organizationLists = acOrganizationMapper.repeatOrganizationIds(id,configurationTypeId, organizationIds);
        if (!CollectionUtils.isEmpty(organizationLists)) {
            //organizationIds.removeAll(organizationLists);
            Integer organizationId = organizationLists.get(0);
            OrganizationDAO organizationDAO = organizationMapper.selectById(organizationId);
            BizBusinessUtils.bizBusinessException("{} 重复添加报警配置" , organizationDAO.getName());
        }
        //修改AlarmConfigurationDAO
        BeanUtils.copyProperties(configurationAlterRequest, configurationDAO);
        configurationDAO.setAlarmConfigurationTypeId(configurationAlterRequest.getConfigurationTypeId());
        configurationDAO.setDistributionTime(JSON.toJSONString(distributionTime));
        configurationDAO.setUpdateTime(new Date());

        UserResult userResult = redisInfoUtil.getUserResult();
        EntityUtil.setCpyCodeDepCodeIpIdUserId(configurationDAO, userResult);
        configurationMapper.updateById(configurationDAO);

        if (!CollectionUtils.isEmpty(organizationIds)) {
            //删除ac_organization_cameragroup
            HashMap<String, Object> acMap = Maps.newHashMap();
            acMap.put("alarm_configuration_id", id);
            acOrganizationMapper.deleteByMap(acMap);
            //资源关联
            saveAcOrganizationCameraGroup(id, configurationAlterRequest.getOrganizationIds());
        }

        //删除art_ac
        HashMap<String, Object> artAcMap = Maps.newHashMap();
        artAcMap.put("alarm_configuration_id", id);
        artAcMapper.deleteByMap(artAcMap);
        //关联响应类型
        saveArtAc(id, responseTypeIds);
        //删除redis缓存
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        delRedisAlarmConfiguration(ids);
        return null;
    }


    /**
     * 判断相关关联是否错误
     *
     * @param organizationIds  organizationIds
     * @param distributionTime 布防时间
     * @param responseTypeIds  响应类型id
     */
    private void execResources(List<Integer> organizationIds, DistributionTimeEntity distributionTime, List<Integer> responseTypeIds, Integer configurationTypeId) {
        if (CollectionUtils.isEmpty(organizationIds)) {
            BizBusinessUtils.bizBusinessException("请关联资源");
        }
        //判断组织机构和镜头组id是否存在
        if (!CollectionUtils.isEmpty(organizationIds)) {
            List<OrganizationDAO> organizationList = organizationMapper.selectListById(organizationIds);
            if (CollectionUtils.isEmpty(organizationList) || organizationList.size() != organizationIds.size()) {
                BizBusinessUtils.bizBusinessException("组织机构id有错误");
            }
        }
        //判断布防时间是否符合规则
        isDistributionTimeEntity(distributionTime);
        //响应类型是否正确
        List<AlarmResponseTypeDAO> responseTypeList = responseTypeMapper.selectBatchIds(responseTypeIds);
        if (CollectionUtils.isEmpty(responseTypeList) || responseTypeList.size() != responseTypeIds.size()) {
            BizBusinessUtils.bizBusinessException("响应类型id有错误");
        }

    }

    private void saveArtAc(int id, List<Integer> responseTypeIds) {
        List<ArtAcDAO> artAcList = new ArrayList<>();
        responseTypeIds.forEach(responseTypeId -> {
            ArtAcDAO artAcDAO = new ArtAcDAO();
            artAcDAO.setAlarmConfigurationId(id);
            artAcDAO.setAlarmResponseTypeId(responseTypeId);
            artAcDAO.setCreateTime(new Date());
            artAcList.add(artAcDAO);
        });
        artAcMapper.insertList(artAcList);
    }

    private void saveAcOrganizationCameraGroup(int id, List<Integer> organizationIds) {
        if (!CollectionUtils.isEmpty(organizationIds)) {
            List<AcOrganizationDAO> acList = new ArrayList<>();
            organizationIds.forEach(organizationId -> {
                AcOrganizationDAO acOrganization = new AcOrganizationDAO();
                acOrganization.setAlarmConfigurationId(id);
                acOrganization.setOrganizationId(organizationId);
                acOrganization.setCreateTime(new Date());
                acList.add(acOrganization);
            });
            acOrganizationMapper.insertList(acList);
        }
    }

    private void isDistributionTimeEntity(DistributionTimeEntity distributionTime) {
        //周一
        DistributionTimeEntity.DisTime monday = distributionTime.getMonday();
        String mondayStartTime = monday.getStartTime();
        String mondayEndTime = monday.getEndTime();
        isEndTimeGreaterStartTime(mondayStartTime, mondayEndTime);

        DistributionTimeEntity.DisTime tuesday = distributionTime.getTuesday();
        String tuesdayStartTime = tuesday.getStartTime();
        String tuesdayEndTime = tuesday.getEndTime();
        isEndTimeGreaterStartTime(tuesdayStartTime, tuesdayEndTime);

        DistributionTimeEntity.DisTime wednesday = distributionTime.getWednesday();
        String wednesdayStartTime = wednesday.getStartTime();
        String wednesdayEndTime = wednesday.getEndTime();
        isEndTimeGreaterStartTime(wednesdayStartTime, wednesdayEndTime);

        DistributionTimeEntity.DisTime thursday = distributionTime.getThursday();
        String thursdayStartTime = thursday.getStartTime();
        String thursdayEndTime = thursday.getEndTime();
        isEndTimeGreaterStartTime(thursdayStartTime, thursdayEndTime);

        DistributionTimeEntity.DisTime friday = distributionTime.getFriday();
        String fridayStartTime = friday.getStartTime();
        String fridayEndTime = friday.getEndTime();
        isEndTimeGreaterStartTime(fridayStartTime, fridayEndTime);

        DistributionTimeEntity.DisTime saturday = distributionTime.getSaturday();
        String saturdayStartTime = saturday.getStartTime();
        String saturdayEndTime = saturday.getEndTime();
        isEndTimeGreaterStartTime(saturdayStartTime, saturdayEndTime);

        DistributionTimeEntity.DisTime sunday = distributionTime.getSunday();
        String sundayStartTime = sunday.getStartTime();
        String sundayEndTime = sunday.getEndTime();
        isEndTimeGreaterStartTime(sundayStartTime, sundayEndTime);

        if (StringUtils.isEmpty(mondayStartTime) && StringUtils.isEmpty(mondayEndTime)
                && StringUtils.isEmpty(tuesdayStartTime) && StringUtils.isEmpty(tuesdayEndTime)
                && StringUtils.isEmpty(wednesdayStartTime) && StringUtils.isEmpty(wednesdayEndTime)
                && StringUtils.isEmpty(thursdayStartTime) && StringUtils.isEmpty(thursdayEndTime)
                && StringUtils.isEmpty(fridayStartTime) && StringUtils.isEmpty(fridayEndTime)
                && StringUtils.isEmpty(saturdayStartTime) && StringUtils.isEmpty(saturdayEndTime)
                && StringUtils.isEmpty(sundayStartTime) && StringUtils.isEmpty(sundayEndTime)) {
            BizBusinessUtils.bizBusinessException("布防时间不能为空");
        }
    }

    private void isEndTimeGreaterStartTime(String startTime, String endTime) {
        boolean flag = (StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime))
                || (!StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime));
        if (flag) {
            BizBusinessUtils.bizBusinessException("布防时间格式错误");
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            long betweenDay = 0;
            try {
                Date start = DateUtil.parse(startTime);
                Date end = DateUtil.parse(endTime);
                betweenDay = DateUtil.between(start, end, DateUnit.MINUTE);
            } catch (Exception e) {
                BizBusinessUtils.bizBusinessException("布防时间格式错误");
            }
            if (betweenDay <= 0) {
                BizBusinessUtils.bizBusinessException("布防时间结束时间必须大于开始时间");
            }
        }
    }


    @Override
    public List<ConfigurationTypeVO> findConfigurationType() {
        List<AlarmConfigurationTypeDAO> configurationTypeDAOList = configurationTypeMapper.selectByMap(null);
        List<ConfigurationTypeVO> configurationTypeVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(configurationTypeDAOList)) {
            configurationTypeDAOList.forEach(configurationTypeDAO -> {
                ConfigurationTypeVO configurationTypeVO = new ConfigurationTypeVO();
                BeanUtils.copyProperties(configurationTypeDAO, configurationTypeVO);
                configurationTypeVOList.add(configurationTypeVO);
            });
        }
        return configurationTypeVOList;
    }

    @Resource
    private ActArtMapper actArtMapper;

    @Override
    public List<ResponseTypeVO> findResponseType(Integer configurationTypeId) {
        QueryWrapper<ActArtDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("alarm_configuration_type_id", configurationTypeId);
        List<ActArtDAO> actArtDAOList = actArtMapper.selectList(queryWrapper);

        List<Integer> ids = new ArrayList<>();
        actArtDAOList.forEach(actArtDAO -> ids.add(actArtDAO.getAlarmResponseTypeId()));

        List<AlarmResponseTypeDAO> responseTypeDAOList = responseTypeMapper.selectBatchIds(ids);

        List<ResponseTypeVO> responseTypeVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(responseTypeDAOList)) {
            responseTypeDAOList.forEach(alarmResponseTypeDAO -> {
                ResponseTypeVO responseTypeVO = new ResponseTypeVO();
                BeanUtils.copyProperties(alarmResponseTypeDAO, responseTypeVO);
                responseTypeVOList.add(responseTypeVO);
            });
        }
        return responseTypeVOList;
    }

    @Resource
    private AlarmConfigurationMapper alarmConfigurationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String configurationDel(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        //删除redis缓存 获取devId 和 报警类型
        delRedisAlarmConfiguration(ids);

        //删除 alarm_configuration
        configurationMapper.deleteBatchIds(ids);
        //删除 ac_organization
        QueryWrapper<AcOrganizationDAO> acWrapper = new QueryWrapper<>();
        acWrapper.in("alarm_configuration_id", ids);
        acOrganizationMapper.delete(acWrapper);
        //删除 art_ac
        QueryWrapper<ArtAcDAO> artAcWrapper = new QueryWrapper<>();
        artAcWrapper.in("alarm_configuration_id", ids);
        artAcMapper.delete(artAcWrapper);
        return null;
    }

    private void delRedisAlarmConfiguration(List<Integer> ids) {
        List<ConfigurationDTO> configurationList = alarmConfigurationMapper.findConfigurationTypeAndOrganization(ids);
        if (!CollectionUtils.isEmpty(configurationList)) {
            configurationList.forEach(configuration -> {
                Integer alarmConfigurationTypeId = configuration.getAlarmConfigurationTypeId();
                List<DeviceDAO> deviceList = configuration.getDeviceList();
                if (!CollectionUtils.isEmpty(deviceList)) {
                    for (DeviceDAO device : deviceList) {
                        Integer id = device.getId();
                        redisInfoUtil.delAlarmConfigurationEntity(id, alarmConfigurationTypeId);
                    }
                }
            });
        }
    }


    @Override
    public PageInfo<AlarmConfigurationVO> findConfiguration(AlarmConfigurationDTO alarmConfigurationDTO) {
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
        List<String> depCodeList = dataPermission.getDepCodeList();

        //根据code 查询组织机构
        QueryWrapper<OrganizationDAO> queryWrapper = new QueryWrapper<>();
        if (!CollectionUtils.isEmpty(depCodeList)) {
            queryWrapper.in("department_code", depCodeList);
        }
        List<OrganizationDAO> organizationList = organizationMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(organizationList)) {
            List<Integer> organizationId = organizationList.stream().map(OrganizationDAO::getId).collect(Collectors.toList());

            QueryWrapper<AcOrganizationDAO> acQueryWrapper = new QueryWrapper<>();
            acQueryWrapper.in("organization_id", organizationId);
            List<AcOrganizationDAO> acOrganizationDAOS = acOrganizationMapper.selectList(acQueryWrapper);
            if (!CollectionUtils.isEmpty(acOrganizationDAOS)) {
                List<Integer> alarmConfigurationIds = acOrganizationDAOS.stream().map(AcOrganizationDAO::getAlarmConfigurationId).collect(Collectors.toList());
                alarmConfigurationDTO.setAlarmConfigurationIds(alarmConfigurationIds);

                PageHelper.startPage(alarmConfigurationDTO.getPageNum(), alarmConfigurationDTO.getPageSize());
                List<AlarmConfigurationVO> configurationList = configurationMapper.findConfiguration(alarmConfigurationDTO);
                return new PageInfo<>(configurationList);
            }
        }
        return new PageInfo<>();
    }

    @Override
    public AlarmConfigurationOneVO findConfigurationOne(Integer id) {
        return configurationMapper.findConfigurationOne(id);
    }

}

