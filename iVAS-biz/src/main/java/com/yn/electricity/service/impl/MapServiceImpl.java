package com.yn.electricity.service.impl;

import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.dao.PlatformVideoInDAO;
import com.yn.electricity.mapper.CameraMapper;
import com.yn.electricity.mapper.DeviceMapper;
import com.yn.electricity.mapper.PlatformMapper;
import com.yn.electricity.mapper.PlatformVideoInMapper;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.DeviceDTO;
import com.yn.electricity.service.MapService;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.vo.PlatformMapVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * Date 2021/6/1 17:06
 * Description 地图相关
 **/
@Service
public class MapServiceImpl implements MapService {

    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private PlatformMapper platformMapper;
    @Resource
    private RedisInfoServiceImpl redisInfoService;
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private PlatformVideoInMapper platformVideoInMapper;
    @Resource
    private DataPermissionUtil dataPermissionUtil;

    @Override
    public List<DeviceDTO> mapFindDevice() {
        List<DeviceDTO> deviceList = redisInfoUtil.getDeviceList();
        if (CollectionUtils.isEmpty(deviceList)) {
            deviceList = deviceMapper.selectDeviceAndCamera();
            redisInfoUtil.setDevice(deviceList);
        }
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
        List<String> depCodeList = dataPermission.getDepCodeList();

        List<DeviceDTO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(depCodeList)){
            if (deviceList != null){
                for (DeviceDTO deviceDTO : deviceList) {
                    String departmentCode = deviceDTO.getDepartmentCode();
                    boolean departmentContains = depCodeList.contains(departmentCode);
                    if (departmentContains){
                        list.add(deviceDTO);
                    }
                }
            }
            return list;
        }else {
            return deviceList;
        }
    }

    @Override
    public String mapCameraLonLat(Integer cameraId, Double lng, Double lat) {
        CameraDAO cameraDAO = cameraMapper.selectById(cameraId);
        if (cameraDAO == null) {
            BizBusinessUtils.bizBusinessException("cameraId 错误");
        }
        cameraDAO.setLongitude(lng);
        cameraDAO.setLatitude(lat);
        cameraMapper.updateById(cameraDAO);

        //更新redis缓存的设备信息
        Integer devId = cameraDAO.getDevId();
        redisInfoService.redisUpdatePreloadingDevice(devId);
        return null;
    }

    @Override
    public List<PlatformMapVO> mapFindPlatform() {
        List<PlatformMapVO> platformMapList = platformMapper.mapFindPlatform();
        if (CollectionUtils.isEmpty(platformMapList)){
            return new ArrayList<>();
        }
        return platformMapList;
    }

    @Override
    public String mapCameraPlatformLonLat(Integer cameraId, Double lng, Double lat) {
        PlatformVideoInDAO platformVideoInDAO = platformVideoInMapper.selectById(cameraId);
        if (platformVideoInDAO == null) {
            BizBusinessUtils.bizBusinessException("cameraId 错误");
        }
        platformVideoInDAO.setLongitude(String.valueOf(lng));
        platformVideoInDAO.setLatitude(String.valueOf(lat));
        platformVideoInMapper.updateById(platformVideoInDAO);
        return null;
    }


}
