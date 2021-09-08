package com.yn.electricity.controller.device;

import com.yn.electricity.ResultVO;
import com.yn.electricity.as.*;
import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.enums.DataEnum;
import com.yn.electricity.result.CameraResult;
import com.yn.electricity.rpc.service.CameraFeignClient;
import com.yn.electricity.service.CameraService;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: zzs
 * @des: 视频联网客户端
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/rest/camera")
@Api(tags = "REST摄像机")
public class CameraController {

    @Resource
    private CameraFeignClient cameraFeignClient;
    @Resource
    private CameraService cameraService;

    @SystemBeforeLog(value = "球机控制")
    @PostMapping("/control")
    @ApiOperation(value = "摄像机控制", notes = "摄像机控制")
    public void control(@RequestBody ParamsControlVO paramsControlVO, HttpServletResponse response) {
        ResultVO<String> control = cameraFeignClient.control(paramsControlVO);
        if(!DataEnum.D_0.getCode().equals(String.valueOf(control.getCode()))){
            BizBusinessUtils.bizBusinessException(control.getMsg());
        }else {
            WebResult.success(control, response);
        }
    }

    @SystemBeforeLog(value = "查询摄像机回放播放信息")
    @PostMapping("/playback")
    @ApiOperation(value = "查询摄像机回放播放信息", notes = "查询摄像机回放播放信息")
    public void playback(@RequestBody List<PlaybackParam> cameraIdList, HttpServletResponse response) {
        ResultVO<List<PlayBackVO>> playback = cameraFeignClient.playback(cameraIdList);
        if(!DataEnum.D_0.getCode().equals(String.valueOf(playback.getCode()))){
            BizBusinessUtils.bizBusinessException(playback.getMsg());
        }else {
            WebResult.success(playback, response);
        }
    }

    @GetMapping("/realplay")
    @ApiOperation(value = "查询摄像机实时播放信息", notes = "返回控件实时播放信息")
    public void realPlay(Integer devId, Integer auxiliaryBitStreamEnable, HttpServletResponse response) {
        ResultVO<PlayBackVO> resultVO = cameraFeignClient.realPlay(devId, auxiliaryBitStreamEnable);
        if(!DataEnum.D_0.getCode().equals(String.valueOf(resultVO.getCode()))){
            BizBusinessUtils.bizBusinessException(resultVO.getMsg());
        }else {
            WebResult.success(resultVO, response);
        }
    }


    @PostMapping("/queryTime")
    @ApiOperation(value = "查询摄像机回放播放信息", notes = "查询摄像机回放播放信息")
    public void queryTime(@RequestBody List<QueryTimeParam> cameraIdList, HttpServletResponse response) {
        ResultVO<List<QueryTimeVO>> listResultVO = cameraFeignClient.queryTime(cameraIdList);
        if(!DataEnum.D_0.getCode().equals(String.valueOf(listResultVO.getCode()))){
            BizBusinessUtils.bizBusinessException(listResultVO.getMsg());
        }else {
            WebResult.success(listResultVO, response);
        }
    }

    @PostMapping("/select/byDev/id")
    @ApiOperation(value = "根据设备id查询镜头", notes = "根据设备id查询镜头")
    @ApiImplicitParam(name = "devId", value = "设备id", dataType = "Integer", required = true)
    public void selectByDevId(Integer devId, HttpServletResponse response) {
        List<CameraResult> result = cameraService.selectByDevId(devId);
        WebResult.success(result, response);
    }

    @PostMapping("/select/by/camera/group/id")
    @ApiOperation(value = "根据镜头组id查询镜头", notes = "根据镜头组id查询镜头")
    @ApiImplicitParam(name = "cameraGroupId", value = "镜头分组id", dataType = "Integer", required = true)
    public void selectByCameraGroupId(Integer cameraGroupId, HttpServletResponse response) {
        List<CameraDAO> list = cameraService.selectByCameraGroupId(cameraGroupId);
        WebResult.success(list, response);
    }

}
