package com.yn.electricity.controller.device;

import cn.gjing.tools.excel.ExcelFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.DeviceQueryDTO;
import com.yn.electricity.request.DeviceAlterRequest;
import com.yn.electricity.request.DeviceSaveRequest;
import com.yn.electricity.service.DeviceService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.utils.ListUtil;
import com.yn.electricity.vo.DeviceVO;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * Date 2021/3/16 16:29
 * Description 设备管理
 **/
@Api(tags = "设备管理相关")
@RestController
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @SystemBeforeLog(menuName = "设备管理", description = "添加设备")
    @PostMapping("/device/add")
    @RequiresPermissions(value = "/device/add")
    @ApiOperation(value = "添加设备", notes = "添加设备")
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
    public void addDevice(@RequestBody DeviceSaveRequest deviceSaveRequest, HttpServletResponse httpServletResponse) {
        ValidationUtils.checkParam(deviceSaveRequest);
        WebResult.success(deviceService.addDevice(deviceSaveRequest), httpServletResponse);
    }


    @SystemBeforeLog(menuName = "设备管理", description = "修改设备信息")
    @PostMapping("/device/alter")
    @RequiresPermissions(value = "/device/alter")
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
    public void alterDevice(@RequestBody DeviceAlterRequest deviceAlterRequest, HttpServletResponse httpServletResponse) {
        ValidationUtils.checkParam(deviceAlterRequest);
        WebResult.success(deviceService.alterDevice(deviceAlterRequest), httpServletResponse);
    }


    @SystemBeforeLog(menuName = "设备管理", description = "删除设备信息")
    @PostMapping("/device/delete")
    @RequiresPermissions(value = "/device/delete")
    @ApiOperation(value = "删除设备信息", notes = "删除设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "query"),
    })
    public void deleteDevice(@RequestBody List<Integer> ids, HttpServletResponse httpServletResponse) {
        WebResult.success(deviceService.deleteDevice(ids), httpServletResponse);
    }


    @GetMapping("/device/find")
    @ApiOperation(value = "查询设备信息", notes = "查询设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页数量", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "设备名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型 id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "organizationName", value = "所属组织名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "cameraGroupName", value = "所属镜头组名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startDate", value = "创建日期 开始时间", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "endTime", value = "创建日期 结束时间", dataTypeClass = Date.class),
    })
    public void findDevice(DeviceQueryDTO deviceQuery, HttpServletResponse httpServletResponse) {
        PageHelper.startPage(deviceQuery.getPageNum() , deviceQuery.getPageSize());
        List<DeviceVO> device = deviceService.findDevice(deviceQuery);
        WebResult.success(new PageInfo<>(ListUtil.newArrayList(device)), httpServletResponse);
    }

    @SystemBeforeLog(menuName = "设备管理", description = "excel导入设备信息")
    @PostMapping("/device/import")
    @RequiresPermissions(value = "/device/import")
    @ApiOperation(value = "导入设备信息 excel", notes = "导入设备信息 excel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "excelFile", value = "excel文件", dataTypeClass = Integer.class),
    })
    public void importDevice(MultipartFile excelFile , HttpServletResponse httpServletResponse){
        WebResult.success(deviceService.importDevice(excelFile) , httpServletResponse);
    }

    @SystemBeforeLog(menuName = "设备导出", description = "设备导出")
    @PostMapping("/device/export")
    @RequiresPermissions(value = "/device/export")
    @ApiOperation(value = "导出设备信息 excel", notes = "导出设备信息 excel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页数量", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "分页", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "设备名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型 id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "organizationName", value = "所属组织名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "cameraGroupName", value = "所属镜头组名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startDate", value = "创建日期 开始时间", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "endTime", value = "创建日期 结束时间", dataTypeClass = Date.class),
    })
    public void exportDevice(DeviceQueryDTO deviceQuery, HttpServletResponse httpServletResponse){
        List<DeviceVO> device = deviceService.findDevice(deviceQuery);
        ExcelFactory.createWriter(DeviceVO.class, httpServletResponse).write(device).flush();
    }

    @RequestMapping(value = "/select/by/camera/groupId", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "根据镜头分组id查设备", notes = "根据镜头分组id查设备")
    @ApiImplicitParam(name = "id", value = "镜头分组id", required = true, dataType = "Integer")
    public void selectByCameraGroupId(Integer id, HttpServletResponse response) {
        WebResult.success(deviceService.selectByCameraGroupId(id) , response);
    }
}
