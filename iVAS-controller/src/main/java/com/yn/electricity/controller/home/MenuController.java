package com.yn.electricity.controller.home;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.Menu;
import com.yn.electricity.enums.MenuTypeEnum;
import com.yn.electricity.qto.MenuDTO;
import com.yn.electricity.request.MenuAlterRequest;
import com.yn.electricity.request.MenuSaveRequest;
import com.yn.electricity.service.MenuService;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.utils.BizParamCheckUtils;
import com.yn.electricity.utils.cron.StringUtils;
import com.yn.electricity.vo.RoleMenuVo;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: MenuController
 * @Author: zzs
 * @Date: 2021/2/24 15:18
 * @Description: 权限管理
 */
@RestController
@RequestMapping(value = "menu")
@Api(tags = "菜单管理")
public class MenuController {
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private MenuService menuService;

    @RequestMapping(value = "add_menu.json", method = RequestMethod.POST)
    @ApiOperation(value = "添加菜单")
    public void addMenu(@RequestBody MenuSaveRequest entity, HttpServletResponse response){
        WebResult.success(menuService.insert(entity),response);
    }

    @SystemBeforeLog(menuName = "菜单管理", description = "修改菜单")
    @RequiresPermissions(value = "update_by_id.json")
    @RequestMapping(value = "update_by_id.json", method = RequestMethod.POST)
    @ApiOperation(value = "修改菜单")
    public void updateById(@RequestBody MenuAlterRequest entity, HttpServletResponse response) {
        ValidationUtils.checkParam(entity);

        String result = menuService.updateById(entity);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "菜单管理", description = "列表查询")
    @RequestMapping(value = "query_list_page.json", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "列表查询")
    public void queryListPage(MenuDTO menuDTO, HttpServletResponse response, HttpServletRequest request) {
        PageInfo result = menuService.selectListPage(menuDTO);
        WebResult.success(result, response);
    }

    @SystemBeforeLog(menuName = "菜单管理", description = "获取按钮操作权限")
    @RequestMapping(value = "get_operate_permission.json", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "获取按钮操作权限")
    public void getOperatePermission(String menuRoute, HttpServletResponse response){
        if(StringUtils.isEmpty(menuRoute)){
            BizParamCheckUtils.BizParamCheckException("menuRoute is not empty");
        }

        List<Menu> list = new ArrayList<>();
        List<RoleMenuVo> permission = redisInfoUtil.getPermission();
        for (RoleMenuVo menuVo:permission) {
            list.addAll(menuVo.getMenuList());
        }
        List<Menu> code = list.stream().filter(l->l.getMenuRoute().equals(menuRoute)).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(code)){
            WebResult.success(new HashMap<>(), response);
        }
        List<Menu> menuList = null;
        for (Menu cd:code) {
            menuList = list.stream().filter(menu -> MenuTypeEnum.LEAF.getCode().equals(menu.getMenuType())
                    && menu.getParentCode().equals(cd.getCode())).collect(Collectors.toList());
            menuList = menuList.stream().distinct().collect(Collectors.toList());
        }

        Map<String, Boolean> map = Maps.newHashMap();
        if(!CollectionUtils.isEmpty(menuList)){
            menuList.forEach(m->{
                if(StringUtils.isNotEmpty(m.getRouteType())){
                    map.put(m.getRouteType(), true);
                }
            });
        }
        WebResult.success(map, response);
    }

}
