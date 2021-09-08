package com.yn.electricity.task;

import com.yn.electricity.service.LogInfoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ScheduledTaskDiag
 * @Author: zzs
 * @Date: 2021/6/18 13:47
 * @Description:
 */
@Configuration
@EnableScheduling
public class ScheduledTask implements SchedulingConfigurer {

    @Resource
    private LogInfoService logInfoService;

    public List<CronTask> cronTasks(){
        List<CronTask> list = new ArrayList<>();
        // 删除大于三十天的日志
        Runnable deleteLogInfo = () -> logInfoService.deleteTime();
        CronTask deleteLogInfos = new CronTask(deleteLogInfo, "0 0 0 1/1 * ?");
        list.add(deleteLogInfos);

        return list;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setCronTasksList(cronTasks());
    }



}
