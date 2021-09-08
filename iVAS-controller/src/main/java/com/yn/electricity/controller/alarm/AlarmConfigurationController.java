package com.yn.electricity.controller.alarm;

import com.yn.electricity.qto.AlarmConfigurationDTO;
import com.yn.electricity.request.AlarmConfigurationAlterRequest;
import com.yn.electricity.request.AlarmConfigurationRequest;
import com.yn.electricity.service.alarm.AlarmConfigurationService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
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
 * Date 2021/5/20 17:51
 * Description 报警配置
 **/
@Validated
@Api(tags = "报警配置")
@RestController
public class AlarmConfigurationController {


    @Resource
    private AlarmConfigurationService configurationService;

    @ApiOperation(value = "查询配置类型", notes = "查询配置类型")
    @GetMapping("/find/configuration_type")
    public void findConfigurationType(HttpServletResponse response) {
        WebResult.success(configurationService.findConfigurationType(), response);
    }

    @ApiOperation(value = "响应类型查询", notes = "响应类型查询")
    @GetMapping("/find/response_type")
    public void findResponseType(@Valid @NotNull(message = "配置类型id错误") Integer configurationTypeId,
                                 HttpServletResponse response) {
        WebResult.success(configurationService.findResponseType(configurationTypeId), response);
    }

    @SystemBeforeLog(menuName = "报警配置", description = "新增报警配置")
    @ApiOperation(value = "新增配置", notes = "新增配置")
    @PostMapping("/configuration/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "配置名称", dataType = "String"),
            @ApiImplicitParam(name = "configurationTypeId", value = "配置类型id", dataType = "Integer"),
            @ApiImplicitParam(name = "distributionTime", value = "布防时间", dataType = "DistributionTimeEntity"),
            @ApiImplicitParam(name = "organizationIds", value = "组织机构id", dataType = "List"),
            @ApiImplicitParam(name = "responseTypeIds", value = "响应类型id", dataType = "List"),
            @ApiImplicitParam(name = "alarmGrade", value = "报警等级 0一般 1重要 2紧急 3非常紧急", dataType = "Integer"),
            @ApiImplicitParam(name = "remarks", value = "备注", dataType = "String"),
            @ApiImplicitParam(name = "enable", value = "是否启用 false 禁用 true启用", dataType = "Boolean"),
    })
    public void addConfiguration(@RequestBody AlarmConfigurationRequest configurationRequest,
                                 HttpServletResponse response) {
        ValidationUtils.checkParam(configurationRequest);
        WebResult.success(configurationService.addConfiguration(configurationRequest), response);
    }

    @SystemBeforeLog(menuName = "报警配置", description = "修改报警配置")
    @ApiOperation(value = "修改配置", notes = "修改配置")
    @PostMapping("/configuration/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "配置名称", dataType = "String"),
            @ApiImplicitParam(name = "configurationTypeId", value = "配置类型id", dataType = "Integer"),
            @ApiImplicitParam(name = "distributionTime", value = "布防时间", dataType = "DistributionTimeEntity"),
            @ApiImplicitParam(name = "organizationIds", value = "组织机构id", dataType = "List"),
            @ApiImplicitParam(name = "responseTypeIds", value = "响应类型id", dataType = "List"),
            @ApiImplicitParam(name = "alarmGrade", value = "报警等级 0一般 1重要 2紧急 3非常紧急", dataType = "Integer"),
            @ApiImplicitParam(name = "remarks", value = "备注", dataType = "String"),
            @ApiImplicitParam(name = "enable", value = "是否启用 false 禁用 true启用", dataType = "Boolean"),
    })
    public void updateConfiguration(@RequestBody AlarmConfigurationAlterRequest configurationRequest,
                                    HttpServletResponse response) {
        ValidationUtils.checkParam(configurationRequest);
        WebResult.success(configurationService.updateConfiguration(configurationRequest), response);
    }

    @SystemBeforeLog(menuName = "报警配置", description = "删除报警配置")
    @ApiOperation(value = "删除配置", notes = "删除配置")
    @PostMapping("/configuration/delete")
    public void configurationDel(@RequestBody List<Integer> ids,
                                 HttpServletResponse response) {
        WebResult.success(configurationService.configurationDel(ids), response);
    }

    @ApiOperation(value = "查询报警配置", notes = "查询报警配置")
    @GetMapping("/configuration/find")
    public void findConfiguration(AlarmConfigurationDTO alarmConfigurationDTO,
                                  HttpServletResponse response) {
        WebResult.success(configurationService.findConfiguration(alarmConfigurationDTO), response);
    }

    @ApiOperation(value = "查询单个报警配置", notes = "查询单个报警配置")
    @ApiImplicitParam(name = "id" , value =  "报警配置地" , dataType = "Integer")
    @GetMapping("/configuration/find_one")
    public void findConfigurationOne(Integer id,HttpServletResponse response) {
        WebResult.success(configurationService.findConfigurationOne(id), response);
    }

}

