package com.yn.electricity.controller.device;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.service.DevicePasService;
import com.yn.electricity.vo.ServiceVO;
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

/**
 * @author admin
 * Date 2021/3/16 15:18
 * Description pas 接入服务管理
 **/
@Api(tags = "接入服务管理")
@RestController
public class DeviceServiceController {

    @Resource
    private DevicePasService pasService;

    @ApiOperation(value = "查询接入服务", notes = "查询接入服务")
    @GetMapping("/service/find")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页数量", required = false, dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageSize", value = "分页", required = false, dataTypeClass = String.class),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型", required = false, dataTypeClass = String.class)
    })
    public void findService(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "30") Integer pageSize,
                            Integer deviceTypeId,HttpServletResponse servletResponse){
        PageInfo<ServiceVO> pageInfo = pasService.findService(pageNum, pageSize,deviceTypeId);
        WebResult.success(pageInfo, servletResponse);
    }
}
