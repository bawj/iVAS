package com.yn.electricity.service;

import com.yn.electricity.qto.DeviceDTO;
import com.yn.electricity.vo.PlatformMapVO;

import java.util.List;

/**
 * @author 地图
 */
public interface MapService {


    /**
     * 查询地图
     * @return list
     */
    List<DeviceDTO> mapFindDevice();


    /**
     * 修改镜头经纬度
     * @param cameraId cameraId
     * @param lng lng
     * @param lat lat
     * @return String
     */
    String mapCameraLonLat(Integer cameraId, Double lng, Double lat);

    /**
     * 查询平台地图信息
     * @return List
     */
    List<PlatformMapVO> mapFindPlatform();

    String mapCameraPlatformLonLat(Integer cameraId, Double lng, Double lat);
}
