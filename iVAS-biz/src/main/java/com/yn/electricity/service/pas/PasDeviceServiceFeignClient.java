package com.yn.electricity.service.pas;

import com.yn.electricity.ResultVO;
import com.yn.electricity.as.PasPlatformDelete;
import com.yn.electricity.as.PassagewayVO;
import com.yn.electricity.as.PlatformVO;
import com.yn.electricity.pas.*;
import com.yn.electricity.service.pas.impl.PasDeviceServiceFeignClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author openFeign 请求 pas 设备相关
 */
@Component
@FeignClient(value = "ynivpas-manager", contextId = "pasDeviceService", fallback = PasDeviceServiceFeignClientImpl.class)
public interface PasDeviceServiceFeignClient {

    /**
     * 增加多个设备
     *
     * @param pasBaseRequest pasBaseRequest
     * @return ResultVO
     */
    @PostMapping("/save_device")
    ResultVO<String> saveEquipment(@RequestBody PasBaseRequest<List<AddDeviceRequest>> pasBaseRequest);

    /**
     * 增加国标设备
     * @param pasBaseRequest pasBaseRequest
     * @return ResultVO
     */
    @PostMapping("/save_platform")
    ResultVO<String> addPlatform(@RequestBody PasBaseRequest<PasInfo<PasAddPlatformVO>> pasBaseRequest);

    /**
     * 删除多个设备
     *
     * @param deviceList deviceList
     * @return ResultVO
     */
    @PostMapping("/del_device")
    ResultVO<String> delDevice(@RequestBody PasBaseRequest<List<DeleteDeviceRequest>> deviceList);


    /**
     * 发送继电器输出指令
     *
     * @param devId     设备id
     * @param channelNo 通道号
     * @return string
     */
    @PostMapping("/instruction/relay")
    ResultVO<String> instructionRelay(@RequestParam(name = "devId") String devId, @RequestParam(name = "channelNo") Integer channelNo);

    /**
     * 发送镜头抓图指令
     *
     * @param devId     设备id
     * @param channelNo 通道号
     * @return string
     */
    @PostMapping("/instruction/capture")
    ResultVO<String> instructionCapture(@RequestParam(name = "devId") String devId, @RequestParam(name = "channelNo") Integer channelNo);

    /**
     * 发送镜头录像指令
     *
     * @param devId     设备id
     * @param channelNo 通道号
     * @return string
     */
    @PostMapping("/instruction/videotape")
    ResultVO<String> instructionVideotape(@RequestParam(name = "devId") String devId, @RequestParam(name = "channelNo") Integer channelNo);

    /**
     * 设备时间同步
     *
     * @param syncTime syncTime
     * @return ResultVO
     */
    @PostMapping("/device/syncTime")
    ResultVO<String> deviceSyncTime(@RequestParam(name = "syncTime") String syncTime);

    /**
     * 查询通道
     *
     * @param ip               ip
     * @param port             port
     * @param registerAccount  账号
     * @param registerPassword 密码
     * @return ResultVO
     */
    @GetMapping("/find/obtain/passageway")
    ResultVO<List<PassagewayVO>> findObtainPassageway(@RequestParam(name = "ip") String ip,
                                                      @RequestParam(name = "port") Integer port,
                                                      @RequestParam(name = "registerAccount") String registerAccount,
                                                      @RequestParam(name = "registerPassword") String registerPassword);

    /**
     * 视频解码上墙
     *
     * @param decoderId 解码器id
     * @param devId     设备id
     * @param channelNo 通道号
     * @return ResultVO
     */
    @GetMapping("/video/wall")
    ResultVO<String> videoWall(@RequestParam(name = "decoderId") Integer decoderId,
                               @RequestParam(name = "devId") Integer devId,
                               @RequestParam(name = "channelNo") Integer channelNo);


    /**
     * 添加录像计划
     * @param pasBaseRequest pasBaseRequest
     * @return ResultVO
     */
    @PostMapping("/add/video_plan")
    ResultVO<String> sendVideoPlan(@RequestBody PasBaseRequest<PasVideoPlanInfo> pasBaseRequest);


    /**
     * 删除录像计划
     * @param pasBaseRequest pasBaseRequest
     * @return ResultVO
     */
    @PostMapping("/del/video_plan")
    ResultVO<String> sendDelVideoPlan(@RequestBody PasBaseRequest<PasDelVideoPlanInfo> pasBaseRequest);


    /**
     * 删除平台
     * @param deviceList deviceList
     * @return ResultVO
     */
    @PostMapping("/del_platform")
    ResultVO<String> delPlatform(@RequestBody PasBaseRequest<List<DeletePlatformRequest>> deviceList);


    /**
     * 新增平台
     * @param platformVO platformVO
     * @return ResultVO
     */
    @PostMapping("/pas/platform/add")
    ResultVO<String> createOne(@RequestBody List<PlatformVO> platformVO) ;


    /**
     * 删除单个平台
     * @param pasPlatformDeleteList pasPlatformDeleteList
     * @return ResultVO
     */
    @DeleteMapping("/pas/platform/del")
    ResultVO<String> deleteOne(@RequestBody List<PasPlatformDelete> pasPlatformDeleteList);
}
