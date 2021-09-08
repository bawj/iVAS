package com.yn.electricity.mapper;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.entity.AlarmConfigurationEntity;
import com.yn.electricity.qto.ConfigurationDTO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AlarmConfigurationTypeMapperTest extends YniVASApplicationTests {

    @Resource
    private AlarmConfigurationTypeMapper alarmConfigurationTypeMapper;
    @Resource
    private AlarmConfigurationMapper alarmConfigurationMapper;

    @Test
    public void findAA() {
        Map<Object, Object> aa = alarmConfigurationTypeMapper.findAA();
        System.out.println(aa);
    }

    @Test
    public void findAA1() {
        AlarmConfigurationEntity configurationByDevIdAndAlarmTypeId = alarmConfigurationMapper.findConfigurationByDevIdAndAlarmTypeId(140,4,1);
        System.out.println(configurationByDevIdAndAlarmTypeId);
    }
}