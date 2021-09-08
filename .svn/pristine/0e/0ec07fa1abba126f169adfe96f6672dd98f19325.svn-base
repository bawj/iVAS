package com.yn.electricity.util;

import com.yn.electricity.exception.BusinessException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @ClassName: SpringUtils
 * @Author: zzs
 * @Date: 2021/1/14 16:52
 * @Description:
 */
public class SpringUtils {

    private static ApplicationContext applicationContext = null;

    public static void setApplication(ApplicationContext application){
        if(null == applicationContext){
            applicationContext = application;
        }
    }

    public static ApplicationContext checkApplication(){
        if(null == applicationContext){
            throw new BusinessException("ApplicationContext没有注入");
        }
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        checkApplication();
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String beanName){
        checkApplication();
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz){
        checkApplication();
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * 获取所有子类
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz){
        checkApplication();
        return applicationContext.getBeansOfType(clazz);
    }


}
