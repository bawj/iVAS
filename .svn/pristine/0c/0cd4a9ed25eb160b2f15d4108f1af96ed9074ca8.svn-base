package com.yn.electricity.controller.rest;

import com.yn.electricity.entity.UserInfoSO;
import com.yn.electricity.mapper.UserMapper;
import com.yn.electricity.redis.RedisUtil;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.utils.BizParamCheckUtils;
import com.yn.electricity.vo.RoleMenuVo;
import com.yn.electricity.vo.UserRoleMenuVo;
import com.yn.electricity.web.WebResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: UserLogin
 * @Author: zzs
 * @Date: 2021/1/21 11:56
 * @Description:
 */
@RestController
@RequestMapping(value = "login")
public class RPCLoginController {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "rpc_login.json")
    public Object login(String userName, String password){
        UserInfoSO infoSO = new UserInfoSO();

        UserRoleMenuVo userRoleMenuVo = (UserRoleMenuVo)redisUtil.get("USER_INFO" + userName);
        if(null != userRoleMenuVo){
            BeanUtils.copyProperties(userRoleMenuVo, infoSO);
            List<RoleMenuVo> roles = (List<RoleMenuVo>)redisUtil.get("ROLE_MENU_INFO" + userName);
        }
        return infoSO;
    }



}
