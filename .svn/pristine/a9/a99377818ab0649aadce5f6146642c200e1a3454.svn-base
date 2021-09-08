package com.yn.electricity.controller.home;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.controller.device.CameraController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CameraControllerTest
 * @Author: zzs
 * @Date: 2021/4/12 10:09
 * @Description:
 */
public class CameraControllerTest extends YniVASApplicationTests {
    @Resource
    private CameraController cameraController;
    @Autowired(required = false)
    private HttpServletResponse response;

    @Test
    public void selectByCameraGroupId() {
        cameraController.selectByCameraGroupId(18, response);
    }

}
