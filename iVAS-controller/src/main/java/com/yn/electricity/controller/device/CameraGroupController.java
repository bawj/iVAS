package com.yn.electricity.controller.device;

import com.yn.electricity.request.CameraGroupAlterRequest;
import com.yn.electricity.request.CameraGroupSaveRequest;
import com.yn.electricity.service.CameraGroupService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.vo.CameraCameraGroupVO;
import com.yn.electricity.vo.CameraGroupResultVO;
import com.yn.electricity.vo.CameraVO;
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
import java.util.List;

/**
 * @author admin
 * Date 2021/3/16 9:27
 * Description 镜头组管理
 **/
@Api(tags = "镜头组管理")
@RestController
public class CameraGroupController {

    @Resource
    private CameraGroupService cameraGroupService;

    @SystemBeforeLog(menuName = "镜头组管理", description = "添加镜头组")
    @ApiOperation(value = "添加镜头组", notes = "添加镜头组")
    @RequiresPermissions(value = "/organization/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "镜头组名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sport", value = "排序", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cameraGroupId", value = "上级id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cameraIdList", value = "镜头id集合", required = false, dataType = "list", paramType = "query"),
    })
    @PostMapping(value = "/camera_group/add")
    public void addCameraGroup(@RequestBody CameraGroupSaveRequest cameraGroupSaveRequest, HttpServletResponse servletResponse) {
        ValidationUtils.checkParam(cameraGroupSaveRequest);
        WebResult.success(cameraGroupService.addCameraGroup(cameraGroupSaveRequest), servletResponse);
    }

    @SystemBeforeLog(menuName = "镜头组管理", description = "修改镜头组")
    @ApiOperation(value = "修改镜头组", notes = "修改镜头组")
    @PostMapping(value = "/camera_group/alter")
    @RequiresPermissions(value = "/organization/alter")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "组织名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "公司company主键", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cameraGroupId", value = "上级id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cameraIdList", value = "镜头id集合", required = false, dataType = "list", paramType = "query"),
    })
    public void alterCameraGroup(@RequestBody CameraGroupAlterRequest cameraGroupAlterRequest, HttpServletResponse servletResponse) {
        ValidationUtils.checkParam(cameraGroupAlterRequest);
        WebResult.success(cameraGroupService.alterCameraGroup(cameraGroupAlterRequest), servletResponse);
    }

    @SystemBeforeLog(menuName = "镜头组管理", description = "删除镜头组")
    @ApiOperation(value = "删除镜头组", notes = "删除镜头组")
    @PostMapping(value = "/camera_group/delete")
    @RequiresPermissions(value = "/organization/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "query"),
    })
    public void deleteCameraGroup(Integer id, HttpServletResponse servletResponse) {
        WebResult.success(cameraGroupService.deleteCameraGroup(id), servletResponse);
    }

    @ApiOperation(value = "查询所有镜头组", notes = "查询所有镜头组")
    @GetMapping(value = "/find/camera/group/list")
    public void findCameraGroupList(HttpServletResponse response) {
        List<CameraGroupResultVO> result = cameraGroupService.findCameraGroupList();
        WebResult.success(result, response);
    }

    @ApiOperation(value = "根据镜头组id查询镜头", notes = "根据镜头组id查询镜头")
    @GetMapping(value = "/find/camera/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cameraGroupId", value = "镜头组id", required = true, dataType = "Integer", paramType = "query"),
    })
    public void findCameraList(Integer cameraGroupId , HttpServletResponse response) {
        List<CameraVO> result = cameraGroupService.findCameraList(cameraGroupId);
        WebResult.success(result, response);
    }

    @ApiOperation(value = "查询镜头分组及分组镜头", notes = "根据镜头组id查询镜头")
    @GetMapping(value = "/get/camera/group/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cameraGroupId", value = "镜头分组父级id 等级传0", required = true, dataType = "Integer", paramType = "query"),
    })
    public void getCameraGroupList(Integer cameraGroupId, HttpServletResponse response) {
        CameraCameraGroupVO groupList = cameraGroupService.getCameraGroupList(cameraGroupId);
        WebResult.success(groupList, response);
    }


}
