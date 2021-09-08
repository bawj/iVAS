package com.yn.electricity.controller.rest;

import com.yn.electricity.request.LogInfoSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.LogInfoService;
import com.yn.electricity.util.IpAddressUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: RPCLogInfoController
 * @Author: zzs
 * @Date: 2021/3/22 10:26
 * @Description:
 */
@RestController
@RequestMapping(value = "rpc_log_info")
@Api(tags = "日志管理")
public class RpcLogInfoController {

    @Resource
    private LogInfoService logInfoService;
    @Resource
    private RedisInfoUtil redisInfoUtil;

    @SystemBeforeLog(menuName = "日志新增")
    @ApiOperation(value = "日志新增")
    @RequestMapping(value = "insert.json", method = RequestMethod.POST)
    public void insert(LogInfoSaveRequest entity, HttpServletRequest request, HttpServletResponse response) {
        UserResult userResult = redisInfoUtil.getUserResult();
        entity.setOperateName(userResult.getUserName());
        entity.setOperateId("" + userResult.getId());
        entity.setMenuRoute(request.getRequestURI());
        entity.setIp(IpAddressUtil.getIpAddress(request));

        String result = logInfoService.insert(entity);
        WebResult.success(result, response);
    }

}
