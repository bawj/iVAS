package com.yn.electricity.service.pas.impl;

import com.yn.electricity.ResultUtilVO;
import com.yn.electricity.ResultVO;
import com.yn.electricity.as.PasPlatformDelete;
import com.yn.electricity.as.PassagewayVO;
import com.yn.electricity.as.PlatformVO;
import com.yn.electricity.enums.ResultEnum;
import com.yn.electricity.pas.*;
import com.yn.electricity.service.pas.PasDeviceServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author admin
 * Date 2021/3/26 16:18
 * Description openFeign 请求 pas 设备相关
 **/
@Slf4j
@Component
public class PasDeviceServiceFeignClientImpl implements PasDeviceServiceFeignClient {

    @Override
    public ResultVO<String> saveEquipment(PasBaseRequest<List<AddDeviceRequest>> pasBaseRequest) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "设备添加到 pas 失败");
    }

    @Override
    public ResultVO<String> addPlatform(PasBaseRequest<PasInfo<PasAddPlatformVO>> pasBaseRequest) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "设备添加到 pas 失败");
    }

    @Override
    public ResultVO<String> delDevice(PasBaseRequest<List<DeleteDeviceRequest>> deviceList) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "pas 删除失败");
    }

    @Override
    public ResultVO<String> instructionRelay(String devId, Integer channelNo) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "发送继电器指令失败");
    }

    @Override
    public ResultVO<String> instructionCapture(String devId, Integer channelNo) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "发送镜头抓图指令失败");
    }

    @Override
    public ResultVO<String> instructionVideotape(String devId, Integer channelNo) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "发送镜头录像指令失败");
    }

    @Override
    public ResultVO<String> deviceSyncTime(String syncTime) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "发送时间同步指令失败");
    }

    @Override
    public ResultVO<List<PassagewayVO>> findObtainPassageway(String ip, Integer port, String registerAccount, String registerPassword) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "获取通道失败");
    }

    @Override
    public ResultVO<String> videoWall(Integer decoderId, Integer devId, Integer channelNo) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "解码上墙失败");
    }

    @Override
    public ResultVO<String> sendVideoPlan(PasBaseRequest<PasVideoPlanInfo> pasBaseRequest) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "添加失败");
    }

    @Override
    public ResultVO<String> sendDelVideoPlan(PasBaseRequest<PasDelVideoPlanInfo> pasBaseRequest) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "删除失败");
    }

    @Override
    public ResultVO<String> delPlatform(PasBaseRequest<List<DeletePlatformRequest>> deviceList) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "删除失败");
    }

    @Override
    public ResultVO<String> createOne(List<PlatformVO> platformVO) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "平台添加 pas 失败");
    }

    @Override
    public ResultVO<String> deleteOne(List<PasPlatformDelete> pasPlatformDeleteList) {
        return ResultUtilVO.onErrorResult(ResultEnum.FAIL.getCode(), "pas 删除失败");
    }
}
