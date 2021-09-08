package com.yn.electricity.controller.home;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.qto.MenuDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MenuTestController
 * @Author: zzs
 * @Date: 2021/3/15 9:08
 * @Description:
 */
public class MenuTestController extends YniVASApplicationTests {
    @Autowired
    private MenuController menuController;
    @Autowired(required = false)
    private HttpServletResponse response;
    @Autowired(required = false)
    private HttpServletRequest request;

    @Test
    public void selectByAppId() {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setAppId(0);

    }
}
