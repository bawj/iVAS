package com.yn.electricity.service;

import com.yn.electricity.request.CameraGroupAlterRequest;
import com.yn.electricity.request.CameraGroupSaveRequest;
import com.yn.electricity.vo.CameraCameraGroupVO;
import com.yn.electricity.vo.CameraGroupResultVO;
import com.yn.electricity.vo.CameraVO;

import java.util.List;

/**
 * @author 镜头组相关service
 */
public interface CameraGroupService {

    /**
     * 添加镜头组
     * @param cameraGroupSaveRequest 镜头组
     * @return string
     */
    String addCameraGroup(CameraGroupSaveRequest cameraGroupSaveRequest);

    /**
     * 修改镜头组
     * @param cameraGroupAlterRequest 参数
     * @return string
     */
    String alterCameraGroup(CameraGroupAlterRequest cameraGroupAlterRequest);

    /**
     * 删除镜头组
     * @param id id
     * @return string
     */
    String deleteCameraGroup(Integer id);

    /**
     * 查询所有镜头分组
     * @return
     */
    List<CameraGroupResultVO> findCameraGroupList();

    /**
     * 根据镜头组查询镜头
     * @param cameraGroupId cameraGroupId
     * @return List
     */
    List<CameraVO> findCameraList(Integer cameraGroupId);

    /**
     * 根据父级查询子集分组及镜头
     * @param cameraGroupId
     * @return
     */
    CameraCameraGroupVO getCameraGroupList(Integer cameraGroupId);

}
