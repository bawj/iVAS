package com.yn.electricity.controller.map;

import com.yn.electricity.service.MapService;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author admin
 * Date 2021/6/1 11:27
 * Description 地图相关接口
 **/
@Api(tags = "电子地图")
@RestController
public class MapController {

    @Resource
    private MapService mapService;

    @ApiOperation(value = "查询设备" , notes = "查询设备")
    @GetMapping("/map/find_device")
    public void mapFindDevice(HttpServletResponse response){
        WebResult.success(mapService.mapFindDevice(), response);
    }

    @SystemBeforeLog(menuName = "电子地图", description = "修改镜头经纬度")
    @ApiOperation(value = "修改镜头经纬度" , notes = "修改镜头经纬度")
    @GetMapping("/map/camera_lonLat")
    public void mapCameraLonLat(Integer cameraId , Double lng , Double lat,
                                            HttpServletResponse response){
        WebResult.success(mapService.mapCameraLonLat(cameraId , lng , lat), response);
    }


    @ApiOperation(value = "查询平台" , notes = "查询平台")
    @GetMapping("/map/find_platform")
    public void mapFindPlatform(HttpServletResponse response){
        WebResult.success(mapService.mapFindPlatform(), response);
    }

    @SystemBeforeLog(menuName = "电子地图", description = "修改平台镜头经纬度")
    @ApiOperation(value = "修改平台镜头经纬度" , notes = "修改平台镜头经纬度")
    @GetMapping("/map/camera_platform_lonLat")
    public void mapCameraPlatformLonLat(Integer cameraId , Double lng , Double lat,
                                HttpServletResponse response){
        WebResult.success(mapService.mapCameraPlatformLonLat(cameraId , lng , lat), response);
    }

}
