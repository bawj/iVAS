package com.yn.electricity.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: YNApplicationContext
 * @Author: zzs
 * @Date: 2021/2/19 10:52
 * @Description:
 */
@Component
public class YNApplicationContext implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.setApplication(applicationContext);
    }

}
