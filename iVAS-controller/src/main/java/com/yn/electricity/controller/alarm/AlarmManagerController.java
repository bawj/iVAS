package com.yn.electricity.controller.alarm;

import cn.gjing.tools.excel.ExcelFactory;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.qto.AlarmManagerDTO;
import com.yn.electricity.result.execl.LogInfoExcel;
import com.yn.electricity.service.alarm.AlarmManagerService;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.vo.AlarmInfoExportVO;
import com.yn.electricity.vo.AlarmInfoVO;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author admin
 * Date 2021/5/20 17:52
 * Description 报警管理
 **/
@Api(tags = "报警管理")
@RestController
public class AlarmManagerController {

    @Resource
    private AlarmManagerService alarmManagerService;

    @ApiOperation(value = "查询报警类型", notes = "查询报警类型")
    @GetMapping("/alarm/configuration_type")
    public void findAlarmConfigurationType(HttpServletResponse response) {
        WebResult.success(alarmManagerService.findAlarmConfigurationType(), response);
    }

    @ApiOperation(value = "查询报警列表", notes = "查询报警列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cameraName", value = "摄像头名称", dataType = "String"),
            @ApiImplicitParam(name = "organizationId", value = "组织id", dataType = "Integer"),
            @ApiImplicitParam(name = "alarmConfigurationTypeId", value = "报警配置类型", dataType = "Integer"),
            @ApiImplicitParam(name = "alarmGrade", value = "报警等级", dataType = "Integer"),
            @ApiImplicitParam(name = "confirmStatus", value = "确认状态", dataType = "Boolean"),
            @ApiImplicitParam(name = "alarmTimeStart", value = "报警开始时间", dataType = "Date"),
            @ApiImplicitParam(name = "alarmTimeEnd", value = "报警结束时间", dataType = "Date"),
    })
    @PostMapping("/alarm/find")
    public void findAlarm(@RequestBody AlarmManagerDTO alarmManager,
                          HttpServletResponse response) {
        WebResult.success(alarmManagerService.findAlarm(alarmManager), response);
    }

    @SystemBeforeLog(menuName = "确认报警信息", description = "确认报警信息")
    @ApiOperation(value = "确认报警状态", notes = "确认报警状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "列表id", dataType = "Integer"),
            @ApiImplicitParam(name = "remarks", value = "备注", dataType = "String"),
    })
    @PostMapping("/alarm/confirm")
    public void alarmConfirm(Integer id, String remarks,HttpServletResponse response) {
        WebResult.success(alarmManagerService.alarmConfirm(id, remarks), response);
    }

    @ApiOperation(value = "导出")
    @PostMapping(value = "/alarm/export")
    public void alarmConfigurationExport(@RequestBody AlarmManagerDTO alarmManager,
                                         HttpServletResponse response){
        List<AlarmInfoExportVO> alarm = alarmManagerService.alarmConfigurationExport(alarmManager);
        ExcelFactory.createWriter(AlarmInfoExportVO.class, response).write(alarm).flush();
    }
}
