package com.yn.electricity.controller.device;

import com.yn.electricity.qto.PlatformQueryDTO;
import com.yn.electricity.request.PlatformAlterRequest;
import com.yn.electricity.request.PlatformSaveRequest;
import com.yn.electricity.service.PlatformService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * Date 2021/9/3 14:05
 * Description 平台相关
 **/
@Api(tags = "平台管理相关")
@RestController
public class PlatformController {

    @Resource
    private PlatformService platformService;

    @SystemBeforeLog(menuName = "设备管理", description = "添加平台设备")
    @PostMapping("/platform/add")
    @RequiresPermissions(value = "/platform/add")
    @ApiOperation(value = "添加设备", notes = "添加平台设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "设备名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型 id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "serviceId", value = "接入服务id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "organizationId", value = "所属组织id", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cameraGroupId", value = "所属镜头组id", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "注册IP", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "port", value = "端口", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerAccount", value = "注册账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerPassword", value = "注册密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "国标编码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "longitude", value = "经度", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "latitude", value = "纬度", dataType = "String", paramType = "query"),
    })
    public void addPlatform(@RequestBody PlatformSaveRequest platformSaveRequest, HttpServletResponse httpServletResponse) {
        ValidationUtils.checkParam(platformSaveRequest);
        WebResult.success(platformService.addPlatform(platformSaveRequest), httpServletResponse);
    }

    @SystemBeforeLog(menuName = "设备管理", description = "修改设备信息")
    @PostMapping("/platform/alter")
    @RequiresPermissions(value = "/platform/alter")
    @ApiOperation(value = "修改设备信息", notes = "修改设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "设备名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型 id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "serviceId", value = "接入服务id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "organizationId", value = "所属组织id", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cameraGroupId", value = "所属镜头组id", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "注册IP", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "port", value = "端口", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerAccount", value = "注册账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerPassword", value = "注册密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "国标编码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "longitude", value = "经度", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "latitude", value = "纬度", dataType = "String", paramType = "query"),
    })
    public void alterPlatform(@RequestBody PlatformAlterRequest platformAlterRequest, HttpServletResponse httpServletResponse) {
        ValidationUtils.checkParam(platformAlterRequest);
        WebResult.success(platformService.alterPlatform(platformAlterRequest), httpServletResponse);
    }

    @SystemBeforeLog(menuName = "刪除平台信息", description = "刪除平台信息")
    @PostMapping("/platform/delete")
    @RequiresPermissions(value = "/platform/delete")
    @ApiOperation(value = "删除设备信息", notes = "删除设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "query"),
    })
    public void deletePlatform(@RequestBody List<Integer> ids, HttpServletResponse httpServletResponse) {
        WebResult.success(platformService.deletePlatform(ids), httpServletResponse);
    }

    @GetMapping("/platform/find")
    @ApiOperation(value = "查询设备信息", notes = "查询设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页数量", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "设备名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型 id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "startDate", value = "创建日期 开始时间", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "endTime", value = "创建日期 结束时间", dataTypeClass = Date.class),
    })
    public void findPlatform(PlatformQueryDTO platformQuery, HttpServletResponse httpServletResponse) {
        WebResult.success(platformService.findPlatform(platformQuery), httpServletResponse);
    }


    @GetMapping("/platform/find_camera_group")
    @ApiOperation(value = "查询设备信息", notes = "查询设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "platformId", value = "platformId", dataTypeClass = Integer.class),
    })
    public void findPlatformCameraGroup(String platformId, HttpServletResponse httpServletResponse) {
        WebResult.success(platformService.findPlatformCameraGroup(platformId), httpServletResponse);
    }
}
