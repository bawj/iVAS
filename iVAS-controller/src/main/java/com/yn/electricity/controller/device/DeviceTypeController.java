package com.yn.electricity.controller.device;

import com.yn.electricity.service.DeviceTypeService;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author admin
 * Date 2021/3/16 18:02
 * Description
 **/
@Api(tags = "设备类型")
@RestController
public class DeviceTypeController {

    @Resource
    private DeviceTypeService deviceTypeService;

    @GetMapping("/device_type/find")
    @ApiOperation(value = "设备类型查询" , notes = "设备类型查询")
    public void findDeviceType(HttpServletResponse httpServletResponse){
        WebResult.success(deviceTypeService.findDeviceType(), httpServletResponse);
    }


}
