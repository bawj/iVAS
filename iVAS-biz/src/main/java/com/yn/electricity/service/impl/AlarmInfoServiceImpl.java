package com.yn.electricity.service.impl;

import com.yn.electricity.entity.AlarmConfigurationEntity;
import com.yn.electricity.mapper.OrganizationMapper;
import com.yn.electricity.pas.DeviceAlarmRequest;
import com.yn.electricity.service.AlarmInfoService;
import com.yn.electricity.service.alarm.SendAlarmInfoService;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.util.ThreadPoolExecutorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * Date 2021/5/25 14:55
 * Description pas上报报警信息
 **/
@Slf4j
@Service
public class AlarmInfoServiceImpl implements AlarmInfoService {

    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private SendAlarmInfoService alarmInfoService;

    private final ExecutorService executorService = ThreadPoolExecutorUtil.getExecutorService(ThreadPoolExecutorUtil.CORE_POOL_SIZE , ThreadPoolExecutorUtil.MAXIMUM_POOL_SIZE, ThreadPoolExecutorUtil.KEEP_ALIVE_TIME , TimeUnit.MILLISECONDS);

    @Override
    public String deviceAlarmInfo(DeviceAlarmRequest deviceAlarmRequest) {
        log.info("#AlarmInfoServiceImpl.deviceAlarmInfo# pas上报报警信息 msg = {}", deviceAlarmRequest.toString());
        String devId = deviceAlarmRequest.getDevId();
        Integer channelNo = deviceAlarmRequest.getChannelNo();
        //报警类型
        Integer alarmTypeId = deviceAlarmRequest.getAlarmTypeId();
        if (!StringUtils.isEmpty(devId)){
            AlarmConfigurationEntity alarmConfigurationEntity = redisInfoUtil.getAlarmConfigurationEntity(Integer.parseInt(devId), alarmTypeId,channelNo);
            if (alarmConfigurationEntity != null){
                String distributionTime = alarmConfigurationEntity.getDistributionTime();
                if (!StringUtils.isEmpty(distributionTime)){
                    alarmInfoService.verificationAlarm(deviceAlarmRequest, alarmConfigurationEntity);
                }
            }
        }
        return null;
    }
}
