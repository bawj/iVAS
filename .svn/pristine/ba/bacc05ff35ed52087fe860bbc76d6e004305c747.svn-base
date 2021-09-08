package com.yn.electricity.util;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Component;


/**
 * @ClassName: YNLogUtils
 * @Author: zzs
 * @Date: 2021/1/19 14:46
 * @Description: 打印日志工具类
 */
@Component
public class YNLogUtils {
//    @Autowired
//    private Tracer tracer;


    public  void info(Logger logger, String msg, String... message){
        msg = spliceMsg(msg, message);
//        tracer.currentSpan().tag("info:" + new Random().nextInt(10000), msg);
        logger.info(msg);
    }

    public  void error(Logger logger, String msg, String... message){
        msg = spliceMsg(msg, message);
//        tracer.currentSpan().tag("error:" + new Random().nextInt(10000), msg);
        logger.error(msg);
    }

    public  void error(Logger logger, Throwable e, String msg, String... message){
        msg = spliceMsg(msg, message);
//        tracer.currentSpan().tag("error:" + new Random().nextInt(10000), msg);
        logger.error(msg, e);
    }

    /**
     * 拼接信息
     * @param msg
     * @param message
     * @return
     */
    private  String spliceMsg(String msg, String... message){
        if(null != message && message.length > 0){
            Object[] obj = new Object[message.length];
            for (int i = 0; i < message.length; i++) {
                obj[i] = message[i];
            }
            msg = MessageFormatter.arrayFormat(msg, obj).getMessage();
        }
        return msg;
    }

}
