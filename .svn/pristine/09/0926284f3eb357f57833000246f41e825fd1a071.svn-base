package com.yn.electricity.controller.home;

import com.yn.electricity.controller.YniVASApplicationTests;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.mapper.DeviceMapper;
import com.yn.electricity.request.UserAlterRequest;
import com.yn.electricity.request.UserSaveRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @ClassName: UserController
 * @Author: zzs
 * @Date: 2021/1/18 14:52
 * @Description:
 */
public class UserTestController extends YniVASApplicationTests {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserTestController.class);

    @Resource
    private UserController userController;

    @Autowired(required = false)
    private HttpServletResponse response;

    @Resource
    private DeviceMapper deviceMapper;



    @Test
    public void selectUser(){
        List<DeviceDAO> deviceDAOList = deviceMapper.selectByMap(null);
        System.out.println(deviceDAOList);
    }

    /**
     * 注册
     */
    @Test
    public void register(){
        UserSaveRequest entity = new UserSaveRequest();
        entity.setUserName("我是第er个用户");
        entity.setPassword("123456");
        entity.setAppId("1");
        entity.setEmail("10430304622@qq.com");
        entity.setDepCode("1");
        entity.setSex("男");
        entity.setNickname("昵称");
        entity.setPhone("13612345678");
        entity.setParentIpId("0");
        entity.setCompanyCode("1");
        entity.setAvailable("n");
        userController.register(entity, response);
    }

    /**
     * 修改
     */
    @Test
    public void updateById() {
        UserAlterRequest request = new UserAlterRequest();
        request.setId(2);
        request.setPassword("111111");
        request.setPhone("13636363636");
        request.setEmail("1454545@qq.com");
        userController.updateById(request, response);
    }
    /**
     * 删除
     */
    @Test
    public void updateBatchId() {
        userController.updateBatchId(null, response);
    }

}
