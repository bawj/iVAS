package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.qto.CameraDTO;
import com.yn.electricity.qto.OrganizationCameraDTO;
import com.yn.electricity.vo.CameraGroupRestVO;
import com.yn.electricity.vo.VideoPlanVO;

import java.util.List;

/**
 * @ClassName: CameraMapper
 * @Author: zzs
 * @Date: 2021/3/25 16:13
 * @Description: 镜头表对应mapper
 */
public interface CameraMapper extends BaseMapper<CameraDAO> {
    /**
     * 根据镜头分组id查询所有镜头
     * @param cameraGroupId
     * @return
     */
    List<CameraDAO> selectByCameraGroupId(Integer cameraGroupId);

    /**
     * 根据镜头分组id查询所有镜头信息
     * @param cameraGroupId
     * @return
     */
    List<CameraGroupRestVO> selectCameraGroupById(Integer cameraGroupId);


    /**
     * 根据devId 查询镜头
     * @param devId devId
     * @return List
     */
    List<CameraDTO> selectCameraByDevId(Integer devId);

    /**
     * 根据设备id查询镜头离线的数量
     * @param deviceIds deviceIds
     * @return OrganizationCameraDTO
     */
    Integer selectByDeviceIdOffline(List<Integer> deviceIds);

    /**
     * 根据设备id查询镜头在线的数量
     * @param deviceIds deviceIds
     * @return OrganizationCameraDTO
     */
    Integer selectByDeviceIdOnline(List<Integer> deviceIds);

    /**
     * 根据镜头id查询镜头在线的数量
     * @param cameraIds cameraIds
     * @return Integer
     */
    Integer selectByIdOnline(List<Integer> cameraIds);

    /**
     * 根据设备id查询镜头离线的数量
     * @param cameraIds cameraIds
     * @return Integer
     */
    Integer selectByIdOffline(List<Integer> cameraIds);


    /**
     * 根据组织机构查询设备信息
     * @param organizationId organizationId
     * @return list
     */
    List<VideoPlanVO> selectCameraByOrganizationId(Integer organizationId);

}
