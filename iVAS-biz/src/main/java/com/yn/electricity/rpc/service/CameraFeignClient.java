package com.yn.electricity.rpc.service;

import com.yn.electricity.ResultVO;
import com.yn.electricity.as.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author
 * @date Content:
 */
@Service
@FeignClient(value = "ynivpas-manager", contextId = "camera"/*, fallback = CameraFeignClientImpl.class*/)
public interface CameraFeignClient {

    /**
     * 摄像机球体控制
     * @param paramsControlVO
     * @return
     */
    @PostMapping("/control")
    ResultVO<String> control(@RequestBody ParamsControlVO paramsControlVO);

    /**
     * 查询摄像机回放播放信息
     * @param cameraIdList
     * @return
     */
    @PostMapping("/playback")
    ResultVO<List<PlayBackVO>> playback(@RequestBody List<PlaybackParam> cameraIdList) ;

    /**
     * 查询摄像机实时播放信息
     * @param id
     * @param auxiliaryBitStreamEnable
     * @return
     */
    @GetMapping("/realplay")
    ResultVO<PlayBackVO> realPlay(@RequestParam(name = "id")Integer id, @RequestParam(name = "auxiliaryBitStreamEnable", defaultValue = "0") Integer auxiliaryBitStreamEnable) ;

    /**
     * 摄像机回放时间
     * @param cameraIdList
     * @return
     */
    @PostMapping("/queryTime")
    ResultVO<List<QueryTimeVO>> queryTime(@RequestBody List<QueryTimeParam> cameraIdList);

}
