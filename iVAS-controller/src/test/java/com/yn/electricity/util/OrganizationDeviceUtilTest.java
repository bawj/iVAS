package com.yn.electricity.util;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.qto.OrganizationCameraDTO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;


class OrganizationDeviceUtilTest extends YniVASApplicationTests {

    @Resource
    private OrganizationDeviceUtil organizationDeviceUtil;

    @Test
    public void getOrganizationCameraDTO() {
        OrganizationCameraDTO organizationCameraDTO = organizationDeviceUtil.getOrganizationCameraDTO(1);
    }
}