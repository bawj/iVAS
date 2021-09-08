package com.yn.electricity.service.alarm.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.yn.electricity.ResultVO;
import com.yn.electricity.dao.AlarmInfoDAO;
import com.yn.electricity.entity.AlarmConfigurationEntity;
import com.yn.electricity.entity.DistributionTimeEntity;
import com.yn.electricity.enums.ResponseTypeEnum;
import com.yn.electricity.enums.ResultEnum;
import com.yn.electricity.mapper.AlarmInfoMapper;
import com.yn.electricity.pas.DeviceAlarmRequest;
import com.yn.electricity.service.RedisInfoService;
import com.yn.electricity.service.alarm.SendAlarmInfoService;
import com.yn.electricity.service.pas.PasDeviceServiceFeignClient;
import com.yn.electricity.socket.NettyWebSocket;
import com.yn.electricity.vo.AlarmInfoVO;
import com.yn.electricity.vo.ResponseTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author admin
 * Date 2021/5/28 14:54
 * Description 处理报警信息
 **/
@Slf4j
@Service
public class SendAlarmInfoServiceImpl implements SendAlarmInfoService {

    @Resource
    private NettyWebSocket nettyWebSocket;
    @Resource
    private AlarmInfoMapper alarmInfoMapper;
    @Resource
    private PasDeviceServiceFeignClient pasDeviceServiceFeignClient;

    @Override
    public void verificationAlarm(DeviceAlarmRequest deviceAlarmRequest, AlarmConfigurationEntity alarmConfiguration) {
        //检测报警时间是否在布防时间段内
        String alarmTime = deviceAlarmRequest.getAlarmTime();
        String distributionTime = alarmConfiguration.getDistributionTime();
        boolean alarmTimeVerification = isAlarmTimeVerification(alarmTime, distributionTime);
        log.info("#SendAlarmInfoServiceImpl.verificationAlarm# 设备id {} , 通道号 {} 有上报报警 是否在布防时间内 {} ",
                deviceAlarmRequest.getDevId(), deviceAlarmRequest.getChannelNo(), alarmTimeVerification);
        if (alarmTimeVerification) {
            //在布防时间段内 根据 响应方式 响应到前端
            List<ResponseTypeVO> responseTypes = alarmConfiguration.getResponseTypes();
            //是否有报警提示音如果有则和报警信息一起发送给前端
            AtomicBoolean isPlayTone = new AtomicBoolean(false);
            for (ResponseTypeVO responseTypeVO : responseTypes) {
                Integer id = responseTypeVO.getId();
                if (id.equals(ResponseTypeEnum.RELAY_OUTPUT.getStatus())) {
                    //发送 pas 控制继电器输出 指令
                    pasDeviceServiceFeignClient.instructionRelay(deviceAlarmRequest.getDevId(), deviceAlarmRequest.getChannelNo());
                } else if (id.equals(ResponseTypeEnum.PLAY_THE_TONE.getStatus())) {
                    //播放报警提示音
                    isPlayTone.set(true);
                } else if (id.equals(ResponseTypeEnum.CAPTURE.getStatus())) {
                    //发送到 pas 抓图指令
                    ResultVO<String> resultVO = pasDeviceServiceFeignClient.instructionCapture(deviceAlarmRequest.getDevId(), deviceAlarmRequest.getChannelNo());
                    if (resultVO.getCode().equals(ResultEnum.SUCCESS.getCode())) {
                        deviceAlarmRequest.setAlarmSnapshot(resultVO.getData());
                    }
                } else if (id.equals(ResponseTypeEnum.VIDEOTAPE.getStatus())) {
                    //发送到 pas 录像指令
                    pasDeviceServiceFeignClient.instructionVideotape(deviceAlarmRequest.getDevId(), deviceAlarmRequest.getChannelNo());
                }
            }
            //保存报警数据到数据库 并发送到前端
            Integer alarmId = saveAlarm(deviceAlarmRequest, alarmConfiguration);
            //发送到前端
            sendMsg(alarmId, deviceAlarmRequest, alarmConfiguration, isPlayTone);
        }
    }

    private void sendMsg(Integer alarmId, DeviceAlarmRequest deviceAlarmRequest,
                         AlarmConfigurationEntity alarmConfiguration, AtomicBoolean isPlayTone) {
        AlarmInfoVO alarmInfo = new AlarmInfoVO();
        alarmInfo.setId(alarmId);
        alarmInfo.setAlarmTime(deviceAlarmRequest.getAlarmTime());
        alarmInfo.setCameraName(alarmConfiguration.getCameraName());
        alarmInfo.setOrganizationName(alarmConfiguration.getOrganizationName());
        alarmInfo.setAlarmGrade(alarmConfiguration.getAlarmGrade());
        alarmInfo.setTypeName(alarmConfiguration.getAlarmConfigurationTypeName());
        alarmInfo.setAlarmContent(deviceAlarmRequest.getAlarmContent());
        alarmInfo.setConfirmStatus(false);
        alarmInfo.setAlarmSnapshotUrl(deviceAlarmRequest.getAlarmSnapshot());
        alarmInfo.setIsPlayTone(isPlayTone.get());
        nettyWebSocket.sendAllMessages(JSON.toJSONString(alarmInfo));
    }

    /**
     * 保存报警信息到数据库
     *
     * @param deviceAlarmRequest 上报的信息
     * @param alarmConfiguration 配置规则信息
     */
    private Integer saveAlarm(DeviceAlarmRequest deviceAlarmRequest, AlarmConfigurationEntity alarmConfiguration) {
        AlarmInfoDAO alarmInfo = new AlarmInfoDAO();
        alarmInfo.setCameraId(alarmConfiguration.getCameraId());
        alarmInfo.setOrganizationId(alarmConfiguration.getOrganizationId());
        alarmInfo.setAlarmTime(DateUtil.parse(deviceAlarmRequest.getAlarmTime()));
        alarmInfo.setAlarmGrade(alarmConfiguration.getAlarmGrade());
        alarmInfo.setAlarmConfigurationTypeId(alarmConfiguration.getAlarmConfigurationTypeId());
        alarmInfo.setAlarmSnapshotUrl(deviceAlarmRequest.getAlarmSnapshot());
        alarmInfo.setAlarmContent(deviceAlarmRequest.getAlarmContent());
        alarmInfo.setConfirmStatus(false);
        alarmInfoMapper.insert(alarmInfo);

        //更新redis缓存
        redisInfoService.redisUpdatePreloadingDevice(Integer.parseInt(deviceAlarmRequest.getDevId()));
        return alarmInfo.getId();
    }

    @Resource
    private RedisInfoService redisInfoService;


    private boolean isAlarmTimeVerification(String alarmTime, String distributionTime) {
        //报警时间
        Date alarmDate = DateUtil.parse(alarmTime);
        //检测报警时间是星期几
        int i = DateUtil.dayOfWeek(alarmDate);
        DistributionTimeEntity.DisTime disTime = null;
        DistributionTimeEntity distribution = JSON.parseObject(distributionTime, DistributionTimeEntity.class);
        switch (i) {
            case 1:
                //周日
                disTime = distribution.getSunday();
                break;
            case 2:
                //周一
                disTime = distribution.getMonday();
                break;
            case 3:
                //周二
                disTime = distribution.getTuesday();
                break;
            case 4:
                //周三
                disTime = distribution.getWednesday();
                break;
            case 5:
                //周四
                disTime = distribution.getThursday();
                break;
            case 6:
                //周五
                disTime = distribution.getFriday();
                break;
            case 7:
                //周六
                disTime = distribution.getSaturday();
                break;
            default:
        }
        if (disTime != null) {
            String startTime = disTime.getStartTime();
            String endTime = disTime.getEndTime();
            if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date parseStart;
                Date parseBegin;
                Date parseEnd;
                try {
                    parseStart = df.parse(df.format(DateUtil.parse(startTime)));
                    parseBegin = df.parse(df.format(DateUtil.parse(alarmTime)));
                    parseEnd = df.parse(df.format(DateUtil.parse(endTime)));
                    return belongCalendar(parseStart, parseBegin, parseEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private boolean belongCalendar(Date startTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(startTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        return end.after(begin) && begin.after(date);
    }

}
