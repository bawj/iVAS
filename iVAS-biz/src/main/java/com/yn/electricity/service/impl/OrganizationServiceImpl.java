package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.*;
import com.yn.electricity.mapper.*;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.OrganizationCameraDTO;
import com.yn.electricity.request.OrganizationAlterRequest;
import com.yn.electricity.request.OrganizationSaveRequest;
import com.yn.electricity.request.SearchOrganizationRequest;
import com.yn.electricity.result.OrganizationResult;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.OrganizationService;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.EntityUtil;
import com.yn.electricity.util.OrganizationDeviceUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.ListUtil;
import com.yn.electricity.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author admin
 * Date 2021/3/15 16:48
 * Description 组织机构管理
 **/
@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private DataPermissionUtil dataPermissionUtil;
    @Resource
    private AcOrganizationMapper acOrganizationMapper;
    @Resource
    private CameraMapper cameraMapper;


    @Override
    public String addOrganization(OrganizationSaveRequest organizationRequest) {
        String name = organizationRequest.getName();
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("name", name);
        List<OrganizationDAO> organizations = organizationMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(organizations)) {
            BizBusinessUtils.bizBusinessException("组织名称重复");
        }
        Integer organizationId = organizationRequest.getOrganizationId();
        OrganizationDAO og = organizationMapper.selectById(organizationId);
        if (og == null) {
            BizBusinessUtils.bizBusinessException("organizationId 错误");
        }

        OrganizationDAO organizationDAO = new OrganizationDAO();
        BeanUtils.copyProperties(organizationRequest, organizationDAO);

        UserResult userResult = redisInfoUtil.getUserResult();
        OrganizationDAO organization = EntityUtil.setCpyCodeDepCodeIpIdUserId(organizationDAO, userResult);
        organization.setCreateTime(new Date());
        organization.setUpdateTime(new Date());

        //------------------------------
        organization.setDepartmentCode(organizationRequest.getDepCode());
        //------------------------------

        log.info("#OrganizationServiceImpl.addOrganization# 保存组织机构信息 organization = {}", organization.toString());
        organizationMapper.insert(organizationDAO);
        return null;
    }


    @Override
    public String alterOrganization(OrganizationAlterRequest organizationAlterRequest) {
        Integer id = organizationAlterRequest.getId();

        String name = organizationAlterRequest.getName();
        OrganizationDAO organizationDAO = organizationMapper.selectByNameNotId(name, id);
        if (organizationDAO != null) {
            BizBusinessUtils.bizBusinessException("组织名称重复");
        }

        OrganizationDAO organization = organizationMapper.selectById(id);
        if (organization == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }

        Integer organizationId = organizationAlterRequest.getOrganizationId();
        OrganizationDAO ot = organizationMapper.selectById(organizationId);
        if (ot == null) {
            BizBusinessUtils.bizBusinessException("organizationId 错误");
        }
        if (id.equals(organizationId)){
            BizBusinessUtils.bizBusinessException("organizationId 错误");
        }

        BeanUtils.copyProperties(organizationAlterRequest, organization);
        organization.setUpdateTime(new Date());

        UserResult userResult = redisInfoUtil.getUserResult();
        organization.setUpdateUserId(userResult.getId());

        log.info("#OrganizationServiceImpl.alterOrganization# 修改组织机构信息 organization = {}", organization.toString());
        organizationMapper.updateById(organization);
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteOrganization(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        //查询是否有下级如果有则不能删除
        ids.forEach(id -> {
            if (id == 1) {
                //树结构最上层不能删除
                BizBusinessUtils.bizBusinessException("id 错误");
            }
            HashMap<String, Object> organizationMap = Maps.newHashMap();
            organizationMap.put("organization_id", id);
            List<OrganizationDAO> organizationDAOList = organizationMapper.selectByMap(organizationMap);
            if (!CollectionUtils.isEmpty(organizationDAOList)) {
                BizBusinessUtils.bizBusinessException("请先删除下级组织机构");
            }
            //查询是否有关联的设备
            List<DeviceDAO> deviceList = deviceMapper.selectByMap(organizationMap);
            if (!CollectionUtils.isEmpty(deviceList)) {
                BizBusinessUtils.bizBusinessException("请先删除关联的设备");
            }
        });
        organizationMapper.deleteByIdList(ids);

        //删除组织机构下关联的报警配置 和报警信息
        QueryWrapper<AcOrganizationDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("organization_id", ids);
        acOrganizationMapper.delete(queryWrapper);

        return null;
    }

    @Override
    public PageInfo<OrganizationVO> findOrganization(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
        List<OrganizationVO> organizationList = organizationMapper.selectByOrganizationIdAndName(1, name, dataPermission);
        return new PageInfo<>(ListUtil.newArrayList(organizationList));
    }

    @Override
    public OrganizationSubordinateVO organizationSubordinate(Integer id) {
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
        List<String> depCodeList = dataPermission.getDepCodeList();

        OrganizationDAO organizationDAO = organizationMapper.selectByDepCodeList(id, depCodeList);
        if (organizationDAO != null) {
            OrganizationSubordinateVO subordinate = new OrganizationSubordinateVO();
            BeanUtils.copyProperties(organizationDAO, subordinate);
            subordinate.setDepCode(organizationDAO.getDepartmentCode());
            List<OrganizationSubordinateVO> organizationSubordinateList = getOrganizationSubordinateList(subordinate.getId());
            subordinate.setChildOrganization(ListUtil.newArrayList(organizationSubordinateList));
            return subordinate;
        }
        return new OrganizationSubordinateVO();
    }

    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public OrganizationSuperiorVO superiorOrganization(Integer id) {
        OrganizationDAO organization = organizationMapper.selectById(id);
        if (organization == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        Integer organizationId = organization.getOrganizationId();
        OrganizationDAO organizationDAO = organizationMapper.selectById(organizationId);
        if (organizationDAO != null) {
            OrganizationSuperiorVO superior = new OrganizationSuperiorVO();
            BeanUtils.copyProperties(organizationDAO, superior);

            QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code" ,organizationDAO.getDepartmentCode());
            List<Department> departments = departmentMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(departments)){
                Department department = departments.get(0);
                superior.setDepId(department.getId());
                superior.setDepName(department.getName());
            }
            return superior;
        }
        return null;
    }

    @Resource
    private OrganizationDeviceUtil organizationDeviceUtil;

    @Override
    public OrganizationResult findOrganizationAndDevice(Integer id) {
        UserResult userResult = redisInfoUtil.getUserResult();
        String depCode = userResult.getDepCode();
        List<OrganizationAndDeviceVO> organizationList;
        if (null == id) {
            organizationList = organizationMapper.selectByPId(depCode, id);
        }else {
            organizationList = organizationMapper.selectByPId(null, id);
        }

        //添加在线和离线
        if (!CollectionUtils.isEmpty(organizationList)){
            for (OrganizationAndDeviceVO organizationAndDeviceVO : organizationList) {
                OrganizationCameraDTO organizationCameraDTO = organizationDeviceUtil.getOrganizationCameraDTO(organizationAndDeviceVO.getId());
                organizationAndDeviceVO.setOnlineQuantity(organizationCameraDTO.getOnlineQuantity());
                organizationAndDeviceVO.setOfflineQuantity(organizationCameraDTO.getOfflineQuantity());
            }
        }

        OrganizationResult result = new OrganizationResult();
        result.setOrganizationList(organizationList);

        List<DeviceDAO> deviceList = deviceMapper.selectByOrganizationId(id);
        if (!CollectionUtils.isEmpty(deviceList)) {
            List<DeviceResultVO> list = Lists.newArrayList();
            deviceList.stream().forEach(dev -> {
                DeviceResultVO deviceResultVO = new DeviceResultVO();
                BeanUtils.copyProperties(dev, deviceResultVO);
                list.add(deviceResultVO);
            });
            result.setDeviceList(list);
        }

        return result;
    }


    @Override
    public List<CameraVO> findOrganizationCamera(Integer id) {
        OrganizationDAO organization = organizationMapper.selectByDepCode(id, null);
        if (organization != null) {
            //递归获取所有组织机构id
            List<Integer> organizationIdList = new ArrayList<>();
            organizationIdList.add(id);

            List<OrganizationSubordinateVO> organizationSubordinateList = getOrganizationSubordinateList(organization.getId());
            if (!CollectionUtils.isEmpty(organizationSubordinateList)) {
                getOrganizationIdList(organizationIdList, organizationSubordinateList);
            }
            //查询设备
            QueryWrapper<DeviceDAO> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("organization_id", organizationIdList);
            List<DeviceDAO> deviceList = deviceMapper.selectList(queryWrapper);
            List<Integer> deviceIdList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(deviceList)) {
                for (DeviceDAO deviceDAO : deviceList) {
                    deviceIdList.add(deviceDAO.getId());
                }
                //查询镜头
                QueryWrapper<CameraDAO> cameraQuery = new QueryWrapper<>();
                cameraQuery.in("dev_id", deviceIdList);
                List<CameraDAO> cameraList = cameraMapper.selectList(cameraQuery);
                if (!CollectionUtils.isEmpty(cameraList)) {
                    List<CameraVO> cameraVOList = new ArrayList<>();
                    for (CameraDAO cameraDAO : cameraList) {
                        CameraVO camera = new CameraVO();
                        BeanUtils.copyProperties(cameraDAO, camera);
                        cameraVOList.add(camera);
                    }
                    return cameraVOList;
                }
            }
        }
        return new ArrayList<>();
    }


    private void getOrganizationIdList(List<Integer> organizationIdList, List<OrganizationSubordinateVO> organizationSubordinateList) {
        for (OrganizationSubordinateVO subordinateVO : organizationSubordinateList) {
            List<OrganizationSubordinateVO> childOrganization = subordinateVO.getChildOrganization();
            Integer id = subordinateVO.getId();
            organizationIdList.add(id);
            if (!CollectionUtils.isEmpty(childOrganization)) {
                getOrganizationIdList(organizationIdList, childOrganization);
            }
        }
    }

    private List<OrganizationSubordinateVO> getOrganizationSubordinateList(Integer organizationId) {
        List<OrganizationSubordinateVO> subordinateList = organizationMapper.selectByOrganizationId(organizationId);
        if (!CollectionUtils.isEmpty(subordinateList)) {
            subordinateList.forEach(organizationSubordinateVO -> {
                Integer id = organizationSubordinateVO.getId();
                List<OrganizationSubordinateVO> organizationSubordinateList = getOrganizationSubordinateList(id);
                organizationSubordinateVO.setChildOrganization(organizationSubordinateList);
            });
        }
        return subordinateList;
    }


    @Override
    public List<SearchOrganDevCameraVO> searchOrganizationDevCamera(SearchOrganizationRequest searchOrganizationRequest) {
        String name = searchOrganizationRequest.getName();
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();

        return organizationMapper.searchOrganizationDevCamera(dataPermission, name);
    }

}
