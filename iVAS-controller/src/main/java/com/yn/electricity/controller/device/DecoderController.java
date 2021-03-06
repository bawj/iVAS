package com.yn.electricity.controller.device;

import com.yn.electricity.request.DecoderAlterRequest;
import com.yn.electricity.request.DecoderSaveRequest;
import com.yn.electricity.service.DecoderService;
import com.yn.electricity.util.ValidationUtils;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 * Date 2021/6/15 10:25
 * Description 解码器
 **/
@Validated
@Api(tags = "解码器")
@RestController
public class DecoderController {

    @Resource
    private DecoderService decoderService;

    @ApiOperation(notes = "获取输出通道", value = "获取输出通道")
    @GetMapping(value = "/obtain/passageway")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "ip地址", dataType = "String"),
            @ApiImplicitParam(name = "port", value = "端口", dataType = "Integer"),
            @ApiImplicitParam(name = "registerAccount", value = "账号", dataType = "String"),
            @ApiImplicitParam(name = "registerPassword", value = "密码", dataType = "String")
    })
    public void obtainPassageway(@Valid @NotEmpty(message = "ip 不能为空") String ip,
                                 @NotNull(message = "账号 不能为空") Integer port,
                                 @NotEmpty(message = "账号 不能为空") String registerAccount,
                                 @NotEmpty(message = "密码 不能为空") String registerPassword,
                                 HttpServletResponse servletResponse) {
        WebResult.success(decoderService.obtainPassageway(ip, port, registerAccount, registerPassword), servletResponse);
    }

    @ApiOperation(notes = "查询解码器", value = "查询解码器")
    @GetMapping(value = "/decoder/find")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "decoderName", value = "解码器名称", dataType = "String"),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型id", dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "分页", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "分页", dataType = "String")
    })
    public void findDecoder(String decoderName, Integer deviceTypeId, String startTime, String endTime,
                            @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum ,
                            @RequestParam(value = "pageSize" , defaultValue = "10") Integer pageSize,
                            HttpServletResponse servletResponse) {
        WebResult.success(decoderService.findDecoder(decoderName,deviceTypeId,startTime, endTime,pageNum ,pageSize), servletResponse);
    }

    @SystemBeforeLog(menuName = "解码器管理", description = "新增解码器")
    @ApiOperation(notes = "增加解码器", value = "增加解码器")
    @PostMapping(value = "/decoder/add")
    public void addDecoder(@RequestBody DecoderSaveRequest decoder, HttpServletResponse httpServletResponse) {
        ValidationUtils.checkParam(decoder);
        WebResult.success(decoderService.addDecoder(decoder), httpServletResponse);
    }

    @SystemBeforeLog(menuName = "解码器管理", description = "修改解码器信息")
    @ApiOperation(notes = "修改解码器信息", value = "修改解码器信息")
    @PostMapping(value = "/decoder/update/")
    public void updateDecoder(@RequestBody DecoderAlterRequest decoder, HttpServletResponse httpServletResponse) {
        ValidationUtils.checkParam(decoder);
        WebResult.success(decoderService.updateDecoder(decoder), httpServletResponse);
    }

    @ApiOperation(notes = "编辑前查询解码器信息", value = "编辑前查询解码器信息")
    @ApiImplicitParam(name = "decoderId", value = "decoderId", dataType = "Integer")
    @GetMapping(value = "/decoder/find_one/")
    public void findDecoderOne(@Valid @NotNull(message = "id 不能为空") Integer decoderId,
                               HttpServletResponse httpServletResponse) {
        WebResult.success(decoderService.findDecoderOne(decoderId), httpServletResponse);
    }

    @SystemBeforeLog(menuName = "解码器管理", description = "删除解码器")
    @ApiOperation(notes = "删除解码器", value = "删除解码器")
    @ApiImplicitParam(name = "id", value = "解码器id", dataType = "Integer")
    @PostMapping(value = "/decoder/delete/")
    public void deleteDecoder(@RequestBody @Valid @NotNull(message = "id 不能为空") List<Integer> ids,
                              HttpServletResponse httpServletResponse) {
        WebResult.success(decoderService.deleteDecoder(ids), httpServletResponse);
    }
}

