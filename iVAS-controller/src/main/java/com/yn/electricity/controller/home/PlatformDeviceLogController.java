package com.yn.electricity.controller.home;

import com.yn.electricity.service.PlatformDeviceLogService;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * Date 2021/10/9 14:21
 * 设备和平台日志
 **/
@Api(tags = "设备和平台日志")
@RestController
public class PlatformDeviceLogController {

    @Resource
    private PlatformDeviceLogService platformDeviceLogService;

    @ApiOperation(value = "平台日志列表")
    @GetMapping("/platform/log_info")
    public void platformLogInfo(@NotNull(message = "id 不能为空") Integer platformId , HttpServletResponse response){
        WebResult.success(platformDeviceLogService.platformLogInfo(platformId), response);
    }

    @ApiOperation(value = "设备日志列表")
    @GetMapping("/device/log_info")
    public void deviceLogInfo(@NotNull(message = "id 不能为空") Integer devId , HttpServletResponse response){
        WebResult.success(platformDeviceLogService.deviceLogInfo(devId), response);
    }

}
