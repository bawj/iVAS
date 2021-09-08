package com.yn.electricity.controller.pas;

import com.yn.electricity.ResultUtilVO;
import com.yn.electricity.ResultVO;
import com.yn.electricity.pas.DeviceAlarmRequest;
import com.yn.electricity.service.AlarmInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author admin
 * Date 2021/5/25 14:53
 * Description pas 上报报警信息
 **/
@Api(tags = "pas 使用")
@RestController
public class AlarmInfoController {

    @Resource
    private AlarmInfoService alarmInfoService;

    @PostMapping("/pas/device/alarmInfo")
    @ApiOperation(value = "接受报警信息", notes = "接受报警信息")
    public ResultVO<String> deviceAlarmInfo(@RequestBody DeviceAlarmRequest pasBaseRequest){
        return ResultUtilVO.onSuccess(alarmInfoService.deviceAlarmInfo(pasBaseRequest));
    }


}
