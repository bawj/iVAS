package com.yn.electricity.controller.device;

import com.yn.electricity.request.VideoPlanSaveRequest;
import com.yn.electricity.service.VideoPlanService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 * Date 2021/8/2 17:02
 * Description
 **/
@Api(tags = "录像管理")
@RestController
public class VideoPlanController {

    @Resource
    private VideoPlanService videoPlanService;

    @PostMapping("/add/video_plan")
    @RequiresPermissions(value = "/add/video_plan")
    @ApiOperation(value = "保存录像计划", notes = "保存录像计划")
    public void addVideoPlan(@RequestBody List<VideoPlanSaveRequest> videoPlanSaveRequest, HttpServletResponse httpServletResponse) {
        ValidationUtils.checkParam(videoPlanSaveRequest);
        WebResult.success(videoPlanService.addVideoPlan(videoPlanSaveRequest), httpServletResponse);
    }

    @GetMapping("/find/video_plan")
    @ApiOperation(value = "查询录像计划", notes = "查询录像计划")
    public void findVideoPlan(@Valid @NotNull(message = "id 不能为空") Integer organizationId, HttpServletResponse httpServletResponse) {
        WebResult.success(videoPlanService.findVideoPlan(organizationId), httpServletResponse);
    }


}
