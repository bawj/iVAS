package com.yn.electricity.controller.home;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.RoleDTO;
import com.yn.electricity.request.RoleAlterRequest;
import com.yn.electricity.request.RoleSaveRequest;
import com.yn.electricity.result.RoleResult;
import com.yn.electricity.service.RoleService;
import com.yn.electricity.util.ValidationUtils;
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


/**
 * @ClassName: UserController
 * @Author: zzs
 * @Date: 2021/1/18 14:52
 * @Description: 角色管理
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 新增角色
     * @param entity
     * @param response
     */
    @SystemBeforeLog(menuName = "角色管理", description = "新增角色")
    @ApiOperation(value = "新增角色")
    @RequiresPermissions(value = "insert.json")
    @RequestMapping(value = "insert.json", method = RequestMethod.POST)
    public void insert(@RequestBody RoleSaveRequest entity, HttpServletResponse response){
        String result = roleService.insert(entity);
        WebResult.success(result, response);
    }

    /**
     * 修改角色
     * @param entity
     * @return
     */
    @SystemBeforeLog(menuName = "角色管理", description = "修改角色")
    @ApiOperation(value = "修改角色")
    @RequiresPermissions(value = "update_by_id.json")
    @RequestMapping(value = "update_by_id.json", method = RequestMethod.POST)
    public void updateById(@RequestBody RoleAlterRequest entity, HttpServletResponse response){
        ValidationUtils.checkParam(entity);

        String result = roleService.updateById(entity);
        WebResult.success(result, response);
    }

    /**
     * 查询角色列表
     * @param roleDTO
     * @param response
     */
    @SystemBeforeLog(menuName = "角色管理", description = "角色列表")
    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "query_list_page.json", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryListPage(RoleDTO roleDTO, HttpServletResponse response){
        PageInfo pageInfo = roleService.selectPage(roleDTO);
        WebResult.success(pageInfo, response);
    }

    /**
     * 根据ipRoleId查询角色下的权限
     * @param ipRoleId
     * @param response
     */
    @SystemBeforeLog(menuName = "角色管理", description = "角色已有权限查询")
    @ApiOperation(value = "角色已有权限查询(修改时用)")
    @ApiImplicitParam(name = "ipRoleId", value = "角色ipRoleId", dataType = "String", required = true)
    @RequestMapping(value = "query_ip_role_id.json", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryIpRoleId(String ipRoleId, HttpServletResponse response) {
        List<String> result = roleService.selectByIpRoleId(ipRoleId);
        WebResult.success(result, response);
    }

    /**
     * 根据ipRoleId查询角色下的数据权限
     * @param ipRoleId
     * @param response
     */
    @SystemBeforeLog(menuName = "角色管理", description = "角色已有数据权限查询")
    @ApiOperation(value = "角色已有数据权限查询(修改时用)")
    @ApiImplicitParam(name = "ipRoleId", value = "角色ipRoleId", dataType = "String", required = true)
    @RequestMapping(value = "query_by_role_dep.json", method = {RequestMethod.POST, RequestMethod.GET})
    public void selectByRoleDep(String ipRoleId, HttpServletResponse response) {
        List<String> result = roleService.selectByRoleDep(ipRoleId);
        WebResult.success(result, response);
    }

    /**
     * 根据id查询角色信息
     * @param id
     * @param response
     */
    @SystemBeforeLog(menuName = "角色管理", description = "查询角色详情")
    @ApiOperation(value = "查询角色详情")
    @ApiImplicitParam(name = "id", value = "角色id", dataType = "Integer", required = true)
    @RequestMapping(value = "query_id.json", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryId(Integer id, HttpServletResponse response) {
        RoleResult role = roleService.selectOne(id);
        WebResult.success(role, response);
    }

    /**
     * 删除角色
     * @param idList
     * @param response
     */
    @SystemBeforeLog(menuName = "角色管理", description = "删除")
    @RequiresPermissions(value = "delete_by_id.json")
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "idList", value = "角色id", dataType = "List", required = true)
    @RequestMapping(value = "delete_by_id.json", method = {RequestMethod.POST})
    public void deleteById(@RequestBody List<Integer> idList, HttpServletResponse response) {
        String result = roleService.deleteById(idList);
        WebResult.success(result, response);
    }

}
