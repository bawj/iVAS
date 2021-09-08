package com.yn.electricity.controller.home;

import com.yn.electricity.request.DepartmentAlterRequest;
import com.yn.electricity.request.DepartmentSaveRequest;
import com.yn.electricity.service.DepartmentService;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DepartmentController
 * @Author: zzs
 * @Date: 2021/3/12 10:32
 * @Description: 部门控制层
 */

@Api(tags = "部门管理")
@RestController
@RequestMapping(value = "department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 新增部门信息
     * @param request
     * @param response
     */
    @SystemBeforeLog(menuName = "部门管理", description = "新增部门信息")
    @ApiOperation(value = "新增部门信息")
    @RequiresPermissions(value = "insert.json")
    @RequestMapping(value = "insert.json", method = RequestMethod.POST)
    public void insert(@RequestBody DepartmentSaveRequest request, HttpServletResponse response) {
        String result = departmentService.insert(request);
        WebResult.success(result, response);
    }

    /**
     * 修改
     * @param request
     * @param response
     */
    @SystemBeforeLog(menuName = "部门管理", description = "修改部门信息")
    @ApiOperation(value = "修改部门信息")
    @RequiresPermissions(value = "update_by_id.json")
    @RequestMapping(value = "update_by_id.json", method = RequestMethod.POST)
    public void updateById(@RequestBody DepartmentAlterRequest request, HttpServletResponse response) {
        String result = departmentService.updateById(request);
        WebResult.success(result, response);
    }

    /**
     * 根据id删除部门
     * @param idList
     * @param response
     */
    @SystemBeforeLog(menuName = "部门管理", description = "删除部门")
    @ApiOperation(value = "删除部门")
    @RequiresPermissions(value = "delete_by_id.json")
    @ApiImplicitParam(name = "idList", value = "部门id 多个都会隔开", dataType = "String", required = true)
    @RequestMapping(value = "delete_by_id.json", method = RequestMethod.POST)
    public void deleteById(@RequestBody List<Integer> idList, HttpServletResponse response) {
        String result = departmentService.deleteById(idList);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "部门管理", description = "部门列表")
    @ApiOperation(value = "部门列表")
    @RequestMapping(value = "query_list.json", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryList(String disabled, String available, HttpServletResponse response) {
        List<Map<String, Object>> mapList = departmentService.queryList(disabled, available);
        WebResult.success(mapList, response);
    }

}
