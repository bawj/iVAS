package com.yn.electricity.controller.system;

import com.yn.electricity.service.SystemConfigurationService;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * Date 2021/6/4 10:08
 * Description 系统配置
 **/
@Api(tags = "系统配置")
@Validated
@RestController
public class SystemConfigurationController {

    @Resource
    private SystemConfigurationService configurationService;

    @ApiOperation(value = "手动同步时间" , notes = "手动同步时间")
    @PostMapping("/device/time_synchronization")
    public void deviceTimeSynchronization(String syncDate , HttpServletResponse httpServletResponse){
        WebResult.success(configurationService.deviceTimeSynchronization(syncDate),httpServletResponse);
    }

    @ApiOperation(value = "开启或关闭自动同步自动同步" , notes = "开启自动同步")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isOpen" , value = "是否开启自动同步" , dataType = "Boolean" , required = true),
            @ApiImplicitParam(name = "interval" , value = "每隔多少天同步一次" , dataType = "Boolean" , required = true)
    })
    @PostMapping("/device/automatic_synchronization")
    public void deviceAutomaticSynchronization(@Valid @NotNull(message = "参数错误") Boolean isOpen ,
                                               @NotNull(message = "参数错误")Integer interval , HttpServletResponse httpServletResponse){
        WebResult.success(configurationService.deviceAutomaticSynchronization(isOpen,interval),httpServletResponse);
    }

    @ApiOperation(value = "查询是否选中了自动同步" , notes = "查询是否选中了自动同步")
    @GetMapping("/device/find_automatic")
    public void deviceFindAutomatic(HttpServletResponse httpServletResponse){
        WebResult.success(configurationService.deviceFindAutomatic() , httpServletResponse);
    }

}
