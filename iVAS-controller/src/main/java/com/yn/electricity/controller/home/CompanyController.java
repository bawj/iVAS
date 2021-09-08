package com.yn.electricity.controller.home;

import com.yn.electricity.request.CompanyAlterRequest;
import com.yn.electricity.request.CompanySaveRequest;
import com.yn.electricity.service.CompanyService;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CompanyController
 * @Author: zzs
 * @Date: 2021/3/11 17:43
 * @Description:
 */
@RestController
@RequestMapping(value = "company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    /**
     * 新增企业
     * @param request
     * @param response
     */
    @SystemBeforeLog(menuName = "系统管理", description = "新增企业")
    @RequestMapping(value = "insert.json")
    public void insert(CompanySaveRequest request, HttpServletResponse response){
        String result = companyService.insert(request);
        WebResult.success(result, response);
    }

    /**
     * 修改企业信息
     * @param request
     * @param response
     */
    @SystemBeforeLog(menuName = "系统管理", description = "修改企业信息")
    @RequestMapping(value = "update_by_id.json")
    public void updateById(CompanyAlterRequest request, HttpServletResponse response){
        String result = companyService.updateById(request);
        WebResult.success(result, response);
    }

}
