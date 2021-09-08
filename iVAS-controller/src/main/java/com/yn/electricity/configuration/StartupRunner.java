package com.yn.electricity.configuration;

import com.yn.electricity.dao.SystemConfigurationDAO;
import com.yn.electricity.mapper.SystemConfigurationMapper;
import com.yn.electricity.service.RedisInfoService;
import com.yn.electricity.service.SystemConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author redis 预加载
 */
@Slf4j
@Component
public class StartupRunner implements CommandLineRunner {


    @Resource
    private RedisInfoService redisInfoService;
    @Resource
    private SystemConfigurationService systemConfigurationService;
    @Resource
    private SystemConfigurationMapper systemConfigurationMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("...................预加载数据开始");

        deviceInfo();
        //开启设备时间同步定时任务
        syncTime();

        log.info("...................预加载数据结束");
    }

    /**
     * redis 预加载设备信息
     */
    public void deviceInfo(){
        redisInfoService.redisPreloadingDevice();
    }

    private void syncTime(){
        SystemConfigurationDAO systemConfigurationDAO = systemConfigurationMapper.selectById(1);
        if (systemConfigurationDAO != null){
            systemConfigurationService.startSync(systemConfigurationDAO.getEnable() , systemConfigurationDAO.getDateInterval());
        }
    }
}