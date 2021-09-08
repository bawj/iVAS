package com.yn.electricity.controller.home;

import com.yn.electricity.dao.AppWeb;
import com.yn.electricity.service.AppWebService;
import com.yn.electricity.web.WebResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: AppWebController
 * @Author: zzs
 * @Date: 2021/2/23 10:05
 * @Description:
 */
@RestController
@RequestMapping(value = "app_web")
public class AppWebController {

    @Resource
    private AppWebService appWebService;

    /**
     * 查询应用列表
     * @param response
     */
    @RequiresPermissions(value = "query_list.json")
    @RequestMapping(value = "/query_list.json")
    public void queryList(HttpServletResponse response) {
        List<AppWeb> webList = appWebService.queryList();
        WebResult.success(webList, response);
    }

    /**
     * 新增应用
     * @param entity
     * @return
     */
    @RequiresPermissions(value = "insert.json")
    @RequestMapping(value = "/insert.json")
    public void insert(AppWeb entity, HttpServletResponse response){
        String result = appWebService.insert(entity);
        WebResult.success(result, response);
    }

    /**
     * 修改应用
     * @param entity
     * @return
     */
    @RequiresPermissions(value = "update_by_id.json")
    @RequestMapping(value = "/update_by_id.json")
    public void updateById(AppWeb entity, HttpServletResponse response){
        String result = appWebService.updateById(entity);
        WebResult.success(result, response);
    }
}
