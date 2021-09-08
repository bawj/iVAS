package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.dao.CameraGroupCameraDAO;
import com.yn.electricity.dao.CameraGroupDAO;
import com.yn.electricity.mapper.CameraGroupCameraMapper;
import com.yn.electricity.mapper.CameraGroupMapper;
import com.yn.electricity.mapper.CameraMapper;
import com.yn.electricity.qto.CameraGroupDTO;
import com.yn.electricity.request.CameraGroupAlterRequest;
import com.yn.electricity.request.CameraGroupSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.CameraGroupCameraService;
import com.yn.electricity.service.CameraGroupService;
import com.yn.electricity.util.EntityUtil;
import com.yn.electricity.util.OrganizationDeviceUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 * Date 2021/3/16 9:34
 * Description 镜头组相关service
 **/
@Slf4j
@Service
public class CameraGroupServiceImpl implements CameraGroupService {

    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private CameraGroupMapper cameraGroupMapper;
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private CameraGroupCameraMapper cameraGroupCameraMapper;
    @Resource
    private CameraGroupCameraService cameraGroupCameraService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addCameraGroup(CameraGroupSaveRequest cameraGroupSaveRequest) {

        String name = cameraGroupSaveRequest.getName();

        UserResult userResult = redisInfoUtil.getUserResult();
        String ipId = userResult.getIpId();
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("name", name);
        hashMap.put("user_ip_id" , ipId);

        List<CameraGroupDAO> cameraGroupList = cameraGroupMapper.selectByMap(hashMap);
        if (!CollectionUtils.isEmpty(cameraGroupList)) {
            BizBusinessUtils.bizBusinessException("镜头组名称重复");
        }
        List<Integer> cameraIdList = cameraGroupSaveRequest.getCameraIdList();
        //判断是否有重复和id是否正确
        isErrorInsertUpdate(cameraIdList);

        Integer cameraGroupId = cameraGroupSaveRequest.getCameraGroupId();
        CameraGroupDAO cameraGroupDAO = cameraGroupMapper.selectById(cameraGroupId);
        if (cameraGroupDAO == null) {
            BizBusinessUtils.bizBusinessException("cameraGroupId 错误");
        }
        //保存到镜头组表
        CameraGroupDAO group = new CameraGroupDAO();
        BeanUtils.copyProperties(cameraGroupSaveRequest, group);
        CameraGroupDAO groupDAO = EntityUtil.setCpyCodeDepCodeIpIdUserId(group, userResult);
        groupDAO.setCreateTime(new Date());
        groupDAO.setUpdateTime(new Date());
        log.info("#CameraGroupServiceImpl.addCameraGroup# 保存镜头组信息 organization = {}", groupDAO.toString());
        cameraGroupMapper.insert(groupDAO);

        //保存到camera_group_camera
        Integer id = groupDAO.getId();
        saveCameraGroupCamera(id, cameraIdList);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String alterCameraGroup(CameraGroupAlterRequest cameraGroupAlterRequest) {
        Integer id = cameraGroupAlterRequest.getId();

        String name = cameraGroupAlterRequest.getName();
        CameraGroupDAO cameraGroup = cameraGroupMapper.selectByNameNotId(name, id);
        if (cameraGroup != null) {
            BizBusinessUtils.bizBusinessException("镜头组名称重复");
        }
        cameraGroup = cameraGroupMapper.selectById(id);
        if (cameraGroup == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        Integer cameraGroupId = cameraGroup.getCameraGroupId();
        if (cameraGroupId == 0) {
            BizBusinessUtils.bizBusinessException("cameraGroupId 错误");
        }
        if (id.equals(cameraGroupId)){
            BizBusinessUtils.bizBusinessException("cameraGroupId 错误");
        }

        //判断是否有重复和id是否正确
        List<Integer> cameraIdList = cameraGroupAlterRequest.getCameraIdList();
        isErrorInsertUpdate(cameraIdList);



        BeanUtils.copyProperties(cameraGroupAlterRequest, cameraGroup);
        cameraGroup.setUpdateTime(new Date());

        UserResult userResult = redisInfoUtil.getUserResult();
        Integer userId = userResult.getId();
        cameraGroup.setUpdateUserId(userId);
        log.info("#CameraGroupServiceImpl.alterCameraGroup# 修改镜头组信息 organization = {}", cameraGroup.toString());
        cameraGroupMapper.updateById(cameraGroup);

        //中间表 先删除 在保存
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("camera_group_id", id);
        int row = cameraGroupCameraMapper.deleteByMap(map);

        saveCameraGroupCamera(id, cameraIdList);
        return null;
    }


    /**
     * 添加和修改时判断名称是否有重复
     *
     * @param cameraIdList cameraIdList
     */
    private void isErrorInsertUpdate(List<Integer> cameraIdList) {
        if (!CollectionUtils.isEmpty(cameraIdList)) {
            //判断镜头id是否重复
            HashSet<Integer> hashSet = new HashSet<>(cameraIdList);
            if (hashSet.size() != cameraIdList.size()) {
                BizBusinessUtils.bizBusinessException("镜头id 有重复");
            }
            //查询镜头id 是否错误
            QueryWrapper<CameraDAO> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", cameraIdList);
            List<CameraDAO> cameraList = cameraMapper.selectList(queryWrapper);
            if (CollectionUtils.isEmpty(cameraList) || cameraList.size() != cameraIdList.size()) {
                BizBusinessUtils.bizBusinessException("镜头id 错误");
            }
        }
    }

    /**
     * 保存到 camera_group_camera
     *
     * @param cameraGroupId cameraGroupId
     * @param cameraIdList  camera_id
     */
    private void saveCameraGroupCamera(Integer cameraGroupId, List<Integer> cameraIdList) {
        if (!CollectionUtils.isEmpty(cameraIdList)) {
            List<CameraGroupCameraDAO> cameraGroupCameraList = new ArrayList<>();
            cameraIdList.forEach(cameraId -> {
                CameraGroupCameraDAO cameraGroupCamera = new CameraGroupCameraDAO();
                cameraGroupCamera.setCameraId(cameraId);
                cameraGroupCamera.setCameraGroupId(cameraGroupId);
                cameraGroupCameraList.add(cameraGroupCamera);
            });
            cameraGroupCameraService.saveBatch(cameraGroupCameraList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteCameraGroup(Integer id) {
        CameraGroupDAO cameraGroup = cameraGroupMapper.selectById(id);
        if (cameraGroup == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        Integer cameraGroupId = cameraGroup.getCameraGroupId();
        if (cameraGroupId == 0) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        UserResult userResult = redisInfoUtil.getUserResult();
        String ipId = userResult.getIpId();
        //查询当前所有的下级
        List<CameraGroupResultVO> cameraGroupResult = getCameraGroupResult(id,ipId);
        //递归删除镜头组
        recursionDeleteCameraGroup(cameraGroupResult);

        //先删除下级的 在删除当前的
        deleteCameraGroupAndOther(id);

        return null;
    }

    private void recursionDeleteCameraGroup(List<CameraGroupResultVO> cameraGroupResult) {
        for (CameraGroupResultVO cameraGroupResultVO : cameraGroupResult) {
            deleteCameraGroupAndOther(cameraGroupResultVO.getId());
            //删除
            List<CameraGroupResultVO> cameraGroupList = cameraGroupResultVO.getCameraGroupList();
            if (!CollectionUtils.isEmpty(cameraGroupList)) {
                recursionDeleteCameraGroup(cameraGroupList);
            }
        }
    }

    /**
     * 删除镜头组和 关联的表
     *
     * @param id id
     */
    private void deleteCameraGroupAndOther(int id) {
        //删除当前的
        cameraGroupMapper.deleteById(id);
        //删除 camera_group_camera
        QueryWrapper<CameraGroupCameraDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("camera_group_id", id);
        cameraGroupCameraMapper.delete(queryWrapper);
    }

    @Override
    public List<CameraVO> findCameraList(Integer cameraGroupId) {
        List<CameraVO> cameraVOList = new ArrayList<>();

        HashMap<String, Object> cameraGroupCameraMap = Maps.newHashMap();
        cameraGroupCameraMap.put("camera_group_id", cameraGroupId);
        List<CameraGroupCameraDAO> cameraGroupCameraList = cameraGroupCameraMapper.selectByMap(cameraGroupCameraMap);
        if (!CollectionUtils.isEmpty(cameraGroupCameraList)) {
            List<Integer> cameraId = new ArrayList<>();
            cameraGroupCameraList.forEach(cameraGroupCameraDAO -> {
                cameraId.add(cameraGroupCameraDAO.getCameraId());
            });

            List<CameraDAO> cameraDAOList = cameraMapper.selectBatchIds(cameraId);
            if (!CollectionUtils.isEmpty(cameraDAOList)) {
                cameraDAOList.forEach(cameraDAO -> {
                    CameraVO camera = new CameraVO();
                    BeanUtils.copyProperties(cameraDAO, camera);
                    cameraVOList.add(camera);
                });
            }
        }
        return cameraVOList;
    }

    @Override
    public List<CameraGroupResultVO> findCameraGroupList() {
        UserResult userResult = redisInfoUtil.getUserResult();
        String ipId = userResult.getIpId();

        List<CameraGroupResultVO> cameraGroupList = cameraGroupMapper.queryAllGroupByIpId(String.valueOf(0));
        if (!CollectionUtils.isEmpty(cameraGroupList)) {
            cameraGroupList.forEach(cameraGroup -> {
                List<CameraGroupResultVO> cameraGroupResult = getCameraGroupResult(cameraGroup.getId(),ipId);
                cameraGroup.setCameraGroupList(cameraGroupResult);
            });
            return cameraGroupList;
        }
        return new ArrayList<>();
    }

    private List<CameraGroupResultVO> getCameraGroupResult(Integer cameraGroupId,String ipId) {
        List<CameraGroupResultVO> cameraGroupList = cameraGroupMapper.selectByCameraGroupId(cameraGroupId , ipId);
        if (!CollectionUtils.isEmpty(cameraGroupList)) {
            cameraGroupList.forEach(cameraGroup -> {
                Integer id = cameraGroup.getId();
                List<CameraGroupResultVO> cameraGroupResult = getCameraGroupResult(id , ipId);
                cameraGroup.setCameraGroupList(cameraGroupResult);
            });
        }
        return cameraGroupList;
    }

    @Resource
    private OrganizationDeviceUtil organizationDeviceUtil;

    /**
     * 获取镜头分组集合
     *
     * @return List
     */
    @Override
    public CameraCameraGroupVO getCameraGroupList(Integer cameraGroupId) {
        CameraCameraGroupVO groupVO = new CameraCameraGroupVO();

        UserResult userResult = redisInfoUtil.getUserResult();
        String ipId = userResult.getIpId();

        List<CameraGroupDAO> groupDAOList =cameraGroupMapper.selectByCameraGroupIdAndUserIpId(ipId , cameraGroupId);
        if(!CollectionUtils.isEmpty(groupDAOList)){
            List<CameraGroupResVO> resVOList = groupDAOList.stream().map(g -> {
                CameraGroupResVO groupResVO = new CameraGroupResVO();
                groupResVO.setId(g.getId());
                groupResVO.setName(g.getName());
                CameraGroupDTO organizationCameraDTO = organizationDeviceUtil.getCameraGroupDTO(g.getId() , ipId);
                //添加在线和离线
                groupResVO.setOnlineQuantity(organizationCameraDTO.getOnlineQuantity());
                groupResVO.setOfflineQuantity(organizationCameraDTO.getOfflineQuantity());
                return groupResVO;
            }).collect(Collectors.toList());
            groupVO.setGroupResVOList(resVOList);
        }

        List<CameraGroupRestVO> cameraList = cameraMapper.selectCameraGroupById(cameraGroupId);
        groupVO.setCameraList(cameraList);
        return groupVO;
    }
}
