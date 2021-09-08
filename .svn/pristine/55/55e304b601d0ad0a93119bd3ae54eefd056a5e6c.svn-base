package com.yn.electricity.controller.redis;

import com.yn.electricity.ResultUtilVO;
import com.yn.electricity.ResultVO;
import com.yn.electricity.mapper.DeviceMapper;
import com.yn.electricity.qto.DeviceDTO;
import com.yn.electricity.service.RedisInfoService;
import com.yn.electricity.util.RedisInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 * Date 2021/6/1 16:58
 * Description pas 添加设备后发送请求 更新redis缓存
 **/
@Slf4j
@RestController
public class RedisInfoController {

    @Resource
    private RedisInfoService redisInfoService;

    @PostMapping(value = "/pas/redis/update_device")
    public ResultVO<String> redisUpdateDevice(Integer devId) {
        log.info("#RedisInfoController.redisUpdateDevice# devId = {} " , devId);
        redisInfoService.redisUpdatePreloadingDevice(devId);
        return ResultUtilVO.onSuccess();
    }

}
