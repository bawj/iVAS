package com.yn.electricity.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yn.electricity.dao.CameraGroupCameraDAO;
import com.yn.electricity.dao.CameraGroupDAO;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.dao.OrganizationDAO;
import com.yn.electricity.mapper.*;
import com.yn.electricity.qto.CameraGroupDTO;
import com.yn.electricity.qto.OrganizationCameraDTO;
import com.yn.electricity.vo.CameraGroupResultVO;
import com.yn.electricity.vo.OrganizationSubordinateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * Date 2021/7/1 14:34
 * Description
 **/
@Slf4j
@Component
public class OrganizationDeviceUtil {

    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private CameraGroupMapper cameraGroupMapper;
    @Resource
    private CameraGroupCameraMapper cameraGroupCameraMapper;

    /**
     * 查询当前组织机构下的镜头在线数量和离线数量
     * @param organizationId organizationId
     * @return OrganizationCameraDTO
     */
    public OrganizationCameraDTO getOrganizationCameraDTO(Integer organizationId){

        List<Integer> organizationIdList = new ArrayList<>();
        OrganizationDAO organizationDAO = organizationMapper.selectById(organizationId);
        organizationIdList.add(organizationDAO.getId());

        List<Integer> ids = getOrganizationIdList(organizationIdList, organizationDAO.getId());
        log.info("#OrganizationDeviceUtil.getOrganizationCameraDTO# id = {} " , JSON.toJSONString(ids));

        QueryWrapper<DeviceDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("organization_id" , ids);
        List<DeviceDAO> deviceDAOList = deviceMapper.selectList(queryWrapper);

        OrganizationCameraDTO organizationCamera = new OrganizationCameraDTO();

        if (!CollectionUtils.isEmpty(deviceDAOList)){
            //查询在线和离线的数量
            List<Integer> deviceIds = deviceDAOList.stream().map(DeviceDAO::getId).collect(Collectors.toList());
            int onlineQuantity = cameraMapper.selectByDeviceIdOnline(deviceIds);
            organizationCamera.setOnlineQuantity(organizationCamera.getOnlineQuantity() + onlineQuantity);
            int offlineQuantity = cameraMapper.selectByDeviceIdOffline(deviceIds);
            organizationCamera.setOfflineQuantity(organizationCamera.getOfflineQuantity() + offlineQuantity);
        }
        return organizationCamera;
    }

    private List<Integer> getOrganizationIdList(List<Integer> organizationIdList,Integer organizationId) {
        List<OrganizationSubordinateVO> subordinateList = organizationMapper.selectByOrganizationId(organizationId);
        if (!CollectionUtils.isEmpty(subordinateList)) {
            subordinateList.forEach(organizationSubordinateVO -> {
                Integer id = organizationSubordinateVO.getId();
                organizationIdList.add(id);
                getOrganizationIdList(organizationIdList,id);
            });
        }
        return organizationIdList;
    }


    public CameraGroupDTO getCameraGroupDTO(Integer cameraGroupId , String ipId){
        List<Integer> cameraGroupIdList = new ArrayList<>();

        CameraGroupDAO cameraGroupDAO = cameraGroupMapper.selectById(cameraGroupId);
        cameraGroupIdList.add(cameraGroupDAO.getId());

        //镜头组id
        List<Integer> groupIds = getCameraGroupIdList(cameraGroupIdList, cameraGroupDAO.getId(),ipId);
        CameraGroupDTO cameraGroupDTO = new CameraGroupDTO();
        if (!CollectionUtils.isEmpty(groupIds)){
            for (Integer groupId : groupIds) {
                QueryWrapper<CameraGroupCameraDAO> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("camera_group_id" , groupId);
                List<CameraGroupCameraDAO> cameraGroupCameraDAOList = cameraGroupCameraMapper.selectList(queryWrapper);
                if (!CollectionUtils.isEmpty(cameraGroupCameraDAOList)){
                    //查询在线和离线的数量
                    List<Integer> cameraIds = cameraGroupCameraDAOList.stream().map(CameraGroupCameraDAO::getCameraId).collect(Collectors.toList());
                    //添加在线和离线
                    Integer onlineQuantity = cameraGroupDTO.getOnlineQuantity();
                    Integer onlineSum = AmountUtil.add(onlineQuantity, cameraMapper.selectByIdOnline(cameraIds), 2);
                    cameraGroupDTO.setOnlineQuantity(onlineSum);

                    Integer offlineQuantity = cameraGroupDTO.getOfflineQuantity();
                    Integer offlineSum = AmountUtil.add(offlineQuantity, cameraMapper.selectByIdOffline(cameraIds), 2);
                    cameraGroupDTO.setOfflineQuantity(offlineSum);
                }
            }
        }
        return cameraGroupDTO;
    }

    private List<Integer> getCameraGroupIdList(List<Integer> cameraGroupIdList,Integer cameraGroupId , String ipId) {
        List<CameraGroupResultVO> cameraGroupList = cameraGroupMapper.selectByCameraGroupId(cameraGroupId , ipId);
        if (!CollectionUtils.isEmpty(cameraGroupList)) {
            cameraGroupList.forEach(cameraGroup -> {
                Integer id = cameraGroup.getId();
                cameraGroupIdList.add(id);
                getCameraGroupIdList(cameraGroupIdList,id , ipId);
            });
        }
        return cameraGroupIdList;
    }
}
