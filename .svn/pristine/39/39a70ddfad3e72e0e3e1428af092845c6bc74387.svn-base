package com.yn.electricity.controller.home;

import com.yn.electricity.enums.DataEnum;
import com.yn.electricity.enums.ExecutionResultEnum;
import com.yn.electricity.enums.MenuTypeEnum;
import com.yn.electricity.redis.RedisUtil;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.security.InterceptorHandler;
import com.yn.electricity.service.MenuService;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.util.RedisKey;
import com.yn.electricity.util.log.annotation.SystemAfterLog;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.BizParamCheckUtils;
import com.yn.electricity.utils.cron.StringUtils;
import com.yn.electricity.web.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserLogin
 * @Author: zzs
 * @Date: 2021/1/21 11:56
 * @Description:
 */
@Api(tags = "登陆管理")
@RestController
@RequestMapping(value = "user_login")
public class LoginController {
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private MenuService menuService;

    /**
     * 手机号或者邮箱登陆
     * @param loginName
     * @param password
     * @param response
     */
    @SystemAfterLog(menuName = "登陆", description = "登陆")
    @ApiOperation(value = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登陆账号", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "登陆密码", dataType = "String", required = true)
    })
    @RequestMapping(value = "login.json", method = {RequestMethod.POST, RequestMethod.GET})
    public void login(String loginName, String password,  HttpServletResponse response){
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
            BizParamCheckUtils.BizParamCheckException("账号或密码为空");
        }

        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
            token.setRememberMe(true);
            try {
                subject.login(token);
            }catch (UnknownAccountException | LockedAccountException e){
                BizParamCheckUtils.BizParamCheckException(e.getMessage());
            }catch (IncorrectCredentialsException e){
                BizParamCheckUtils.BizParamCheckException("用户名或密码错误", e);
            }
        }

        UserResult user = redisInfoUtil.getUserResult();
        List<Map<String, Object>> mapList = menuService.getOperatePermission(DataEnum.D_0.getCode(), MenuTypeEnum.NORMAL.getCode());
        if(CollectionUtils.isEmpty(mapList)){
            redisUtil.del(RedisKey.USER_INFO + subject.getPrincipal());
            redisUtil.del(RedisKey.ROLE_MENU_INFO + subject.getPrincipal());
            subject.logout();
            BizBusinessUtils.bizBusinessException("该用户未有任何权限,请联系管理员分配 login:{}", loginName);
        }
        user.setMapList(mapList);
        WebResult.success(user, response);
    }

    @Resource
    private RedisInfoUtil redisInfoUtil;

    /**
     * 推出登陆
     * @param response
     */
    @SystemBeforeLog(menuName = "登陆退出", description = "登陆退出")
    @ApiOperation(value = "登陆退出")
    @RequestMapping(value = "login_out.json", method = {RequestMethod.POST, RequestMethod.GET})
    public void loginOut(HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            redisUtil.del(RedisKey.USER_INFO + subject.getPrincipal());
            redisUtil.del(RedisKey.ROLE_MENU_INFO + subject.getPrincipal());
            String loginName = (String) subject.getPrincipal();
            InterceptorHandler.removeCache(loginName);
            subject.logout();
        }
        WebResult.success(ExecutionResultEnum.SUCCESS.getEnglishName(), response);
    }


}
