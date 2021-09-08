package com.yn.electricity.util.log;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yn.electricity.dao.LogInfo;
import com.yn.electricity.mapper.LogInfoMapper;
import com.yn.electricity.mapper.UserMapper;
import com.yn.electricity.qto.UserDTO;
import com.yn.electricity.util.IpAddressUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.util.log.annotation.SystemAfterLog;
import com.yn.electricity.utils.cron.StringUtils;
import com.yn.electricity.vo.UserRoleMenuVo;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class SysLogAfterPect {
    private final static Logger log = LoggerFactory.getLogger(SysLogAfterPect.class);
    @Autowired(required = false)
    private HttpServletRequest request;
    @Resource
    private LogInfoMapper logInfoMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private UserMapper userMapper;

    @Pointcut("@annotation(com.yn.electricity.util.log.annotation.SystemAfterLog)")
    public void pointcut(){}

    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint){
        LogInfo uhl = new LogInfo();
        String loginName = null;

        //请求接口
        String url = request.getRequestURI();
        Object[] parmeter = joinPoint.getArgs();
        List<Object> array = new ArrayList<>();
        for (int i=0 ;i<parmeter.length;i++) {
            if(!(parmeter[i] instanceof ServletRequest) && !(parmeter[i] instanceof ServletResponse)
                    &&!(parmeter[i] instanceof MultipartFile)){
                array.add(parmeter[i]);
                if("/user_login/login.json".equals(url)){
                    loginName = String.valueOf(parmeter[i]);
                }
            }
        }


        UserDTO userDTO = new UserDTO();

        if(StringUtils.isNotEmpty(loginName)){
            if(loginName.contains("@")){
                userDTO.setPhone(loginName);
            }else {
                userDTO.setEMail(loginName);
            }

        }
        UserRoleMenuVo user = userMapper.selectByLoginName(userDTO);
        if(null == user){
            return;
        }
        uhl.setOperateName(user.getUserName());
        uhl.setOperateId("" + user.getId());

        uhl.setMenuRoute(url);
        uhl.setIp(IpAddressUtil.getIpAddress(request));
        uhl.setCreateTime(new Date());

        if(null == SecurityUtils.getSubject().getPrincipal()){
            Map<String, String> map = Maps.newHashMap();
            map.put("des", "未登录");
            uhl.setExt(JSON.toJSONString(map));
        }

        SystemAfterLog sl = methodName(joinPoint);
        if(null != sl){
            uhl.setMenuName(sl.menuName());
            uhl.setDescription(sl.description());
        }
        log.info(JSON.toJSONString(uhl));
        logInfoMapper.insert(uhl);
    }

    public SystemAfterLog methodName(JoinPoint joinPoint){
        String targetClass = joinPoint.getTarget().getClass().getName();
        String methedName = joinPoint.getSignature().getName();
        Object[] parmeter = joinPoint.getArgs();
        SystemAfterLog logs = null ;
        try {
            Class<?> forName = Class.forName(targetClass);
            Method[] methods = forName.getMethods();
            for (Method method : methods) {
                if(method.getName().equals(methedName)){
                    Class<?>[] methodTypes = method.getParameterTypes();
                    if(methodTypes.length == parmeter.length){
                        logs = method.getAnnotation(SystemAfterLog.class);
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("日志返回对象异常",e);
        }
        return logs;
    }

}
