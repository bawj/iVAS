package com.yn.electricity.util.log;

import com.alibaba.fastjson.JSON;
import com.yn.electricity.dao.LogInfo;
import com.yn.electricity.mapper.LogInfoMapper;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.util.IpAddressUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.util.log.annotation.SystemBeforeLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

@Aspect
@Component
public class SysLogBeforePect {
    private final static Logger log = LoggerFactory.getLogger(SysLogBeforePect.class);
    @Autowired(required = false)
    private HttpServletRequest request;
    @Resource
    private LogInfoMapper logInfoMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;

    @Pointcut("@annotation(com.yn.electricity.util.log.annotation.SystemBeforeLog)")
    public void pointcut(){}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        LogInfo uhl = new LogInfo();
        //请求接口
        String url = request.getRequestURI();
        Object[] parmeter = joinPoint.getArgs();
        List<Object> array = new ArrayList<>();
        for (int i=0 ;i<parmeter.length;i++) {
            if(!(parmeter[i] instanceof ServletRequest) && !(parmeter[i] instanceof ServletResponse)
                    &&!(parmeter[i] instanceof MultipartFile)){
                array.add(parmeter[i]);
            }
        }
        UserResult userResult = redisInfoUtil.getUserResult();
        uhl.setOperateName(userResult.getUserName());
        uhl.setOperateId("" + userResult.getId());
        uhl.setMenuRoute(url);
        uhl.setIp(IpAddressUtil.getIpAddress(request));
        uhl.setCreateTime(new Date());

        SystemBeforeLog sl = methodName(joinPoint);
        if(null != sl){
            uhl.setMenuName(sl.menuName());
            uhl.setDescription(sl.description());
        }
        log.info(JSON.toJSONString(uhl));
        logInfoMapper.insert(uhl);
    }

    public SystemBeforeLog methodName(JoinPoint joinPoint){
        String targetClass = joinPoint.getTarget().getClass().getName();
        String methedName = joinPoint.getSignature().getName();
        Object[] parmeter = joinPoint.getArgs();
        SystemBeforeLog logs = null ;
        try {
            Class<?> forName = Class.forName(targetClass);
            Method[] methods = forName.getMethods();
            for (Method method : methods) {
                if(method.getName().equals(methedName)){
                    Class<?>[] methodTypes = method.getParameterTypes();
                    if(methodTypes.length == parmeter.length){
                        logs = method.getAnnotation(SystemBeforeLog.class);
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
