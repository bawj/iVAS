package com.yn.electricity.mapper;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.qto.DeviceDTO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlarmConfigurationMapperTest extends YniVASApplicationTests {

    @Resource
    private DeviceMapper deviceMapper;

    @Test
    public void findConfigurationTypeAndOrganization() {
        List<DeviceDTO> deviceList = deviceMapper.selectDeviceAndCamera();
        System.out.println(deviceList.toString());
    }

}