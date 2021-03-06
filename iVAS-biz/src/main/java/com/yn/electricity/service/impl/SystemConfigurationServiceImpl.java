package com.yn.electricity.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.cron.Scheduler;
import cn.hutool.cron.task.Task;
import com.yn.electricity.ResultVO;
import com.yn.electricity.dao.SystemConfigurationDAO;
import com.yn.electricity.enums.ResultEnum;
import com.yn.electricity.mapper.SystemConfigurationMapper;
import com.yn.electricity.service.SystemConfigurationService;
import com.yn.electricity.service.pas.PasDeviceServiceFeignClient;
import com.yn.electricity.util.cron.CronUtil;
import com.yn.electricity.vo.SystemConfigurationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * Date 2021/6/4 17:19
 * Description
 **/
@Slf4j
@Service
public class SystemConfigurationServiceImpl implements SystemConfigurationService {


    @Resource
    private PasDeviceServiceFeignClient pasDeviceServiceFeignClient;
    @Resource
    private SystemConfigurationMapper systemConfigurationMapper;

    public static final String AUTOMATIC_SYNCHRONIZATION_CODE = "automatic_synchronization_code";
    private static final Map<String, Scheduler> SCHEDULER_MAP = new HashMap<>();

    @Override
    public String deviceTimeSynchronization(String syncDate) {
        DateTime parse = DateUtil.parse(syncDate, "yyyy-MM-dd HH:mm:ss");
        String s = String.valueOf(parse.getTime() / 1000);
        executeTask(s);
        return null;
    }

    @Override
    public String deviceAutomaticSynchronization(Boolean isOpen ,Integer interval) {
        SystemConfigurationDAO systemConfigurationDAO = systemConfigurationMapper.selectById(1);
        if (systemConfigurationDAO == null){
            systemConfigurationDAO = new SystemConfigurationDAO();
            systemConfigurationDAO.setEnable(isOpen);
            systemConfigurationDAO.setDateInterval(interval);
            systemConfigurationMapper.insert(systemConfigurationDAO);
        }else {
            systemConfigurationDAO.setEnable(isOpen);
            systemConfigurationDAO.setDateInterval(interval);
            systemConfigurationMapper.updateById(systemConfigurationDAO);
        }
        startSync(isOpen,interval);
        return null;
    }

    @Override
    public void startSync(Boolean isOpen, Integer interval) {
        //?????????????????????????????? ??????????????????
        if (SCHEDULER_MAP.size() <= 0 && isOpen){
            //??????????????????????????????????????? ?????????????????????????????? ???????????????????????????????????????????????????????????????
            //?????? interval ???????????????
            String cron = CronUtil.createLoopCronExpression(3, interval);
            Scheduler scheduler = new Scheduler();
            scheduler.schedule(AUTOMATIC_SYNCHRONIZATION_CODE, cron, new Task() {
                @Override
                public void execute() {
                    executeTask(String.valueOf(System.currentTimeMillis()));
                }
            });
            scheduler.setMatchSecond(true);
            scheduler.start();
            SCHEDULER_MAP.put(AUTOMATIC_SYNCHRONIZATION_CODE , scheduler);
        }
    }

    @Override
    public SystemConfigurationVO deviceFindAutomatic() {
        SystemConfigurationVO configuration = new SystemConfigurationVO();
        SystemConfigurationDAO systemConfigurationDAO = systemConfigurationMapper.selectById(1);
        if (systemConfigurationDAO != null){
            BeanUtils.copyProperties(systemConfigurationDAO , configuration);
        }
        return configuration;
    }


    /**
     * ??????????????????
     * @param syncDate ????????????
     */
    private void executeTask(String syncDate) {
        ResultVO<String> resultVO = pasDeviceServiceFeignClient.deviceSyncTime(syncDate);
        if (resultVO.getCode() != ResultEnum.SUCCESS.getCode()){
            log.info("#SystemConfigurationServiceImpl.executeTask# ???????????????????????? {} " , syncDate);
        }
    }

}
