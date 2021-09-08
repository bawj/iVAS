package com.yn.electricity.service;

import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.result.CameraResult;

import java.util.List;

/**
 * @ClassName: CameraService
 * @Author: zzs
 * @Date: 2021/3/25 16:12
 * @Description: 镜头管理
 */
public interface CameraService {
    /**
     * 根据设备id查询镜头
     * @param devId
     * @return
     */
    List<CameraResult> selectByDevId(Integer devId);

    /**
     * 根据镜头分组id查询所有镜头
     * @param cameraGroupId
     * @return
     */
    List<CameraDAO> selectByCameraGroupId(Integer cameraGroupId);
}
