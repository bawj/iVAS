package com.yn.electricity.controller.home;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.service.PlatformDeviceLogService;
import com.yn.electricity.vo.DeviceLogInfoVO;
import com.yn.electricity.vo.PlatformLogInfoVO;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页数量", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "platformId", value = "平台id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataTypeClass = Integer.class),
    })
    public void platformLogInfo(@NotNull(message = "id 不能为空") Integer platformId,
                                @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize" , defaultValue = "100") Integer pageSize,
                                String startTime,
                                String endTime,
                                HttpServletResponse response) {
        PageHelper.startPage(pageNum , pageSize);
        List<PlatformLogInfoVO> platformLogInfoVOList = platformDeviceLogService.platformLogInfo(platformId,startTime,endTime);
        WebResult.success(new PageInfo<>(platformLogInfoVOList), response);
    }

    @ApiOperation(value = "设备日志列表")
    @GetMapping("/device/log_info")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页数量", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "devId", value = "设备id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataTypeClass = Integer.class),
    })
    public void deviceLogInfo(@NotNull(message = "id 不能为空") Integer devId,
                              @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize" , defaultValue = "100") Integer pageSize,
                              String startTime,
                              String endTime,
                              HttpServletResponse response) {
        PageHelper.startPage(pageNum , pageSize);
        List<DeviceLogInfoVO> deviceLogInfoVOList = platformDeviceLogService.deviceLogInfo(devId,startTime,endTime);
        WebResult.success(new PageInfo<>(deviceLogInfoVOList), response);
    }

}
