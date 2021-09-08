package com.yn.electricity.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yn.electricity.enums.ErrorCommonEnum;
import org.slf4j.MDC;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WebResult
 * @Author: zzs
 * @Date: 2021/1/14 8:53
 * @Description:
 */
public class WebResult {

    public static <T> void success(T data, HttpServletResponse response){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }
    public static <T> void success(boolean success, T data, HttpServletResponse response){
        Result<T> result = new Result<>();
        result.setSuccess(success);
        result.setData(data);
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }
    public static <T> void success(String tabType, T data, HttpServletResponse response){
        Result<T> result = new Result<>();
        result.setTabType(tabType);
        result.setData(data);
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }
    public static <T> void success(boolean success, String tabType, T data, HttpServletResponse response){
        Result<T> result = new Result<>();
        result.setSuccess(success);
        result.setTabType(tabType);
        result.setData(data);
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }
    public static void success(boolean success, String tabType, Map<Object, Object> dataMap, HttpServletResponse response){
        Result<Map<Object,Object>> result = new Result<>();
        result.setSuccess(success);
        result.setTabType(tabType);
        result.setData(dataMap);
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }

    public static <T> void error(ErrorCommonEnum commonEnum, ServletResponse response){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setTabType("2");
        result.setErrorCode(commonEnum.getCode());
        result.setErrorMsg(commonEnum.getMsg());
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }
    public static <T> void error(boolean success, String tabType, ErrorCommonEnum commonEnum, HttpServletResponse response){
        Result<T> result = new Result<T>();
        result.setSuccess(success);
        result.setTabType(tabType);
        result.setErrorCode(commonEnum.getCode());
        result.setErrorMsg(commonEnum.getMsg());
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }
    public static <T> void error(boolean success, ErrorCommonEnum commonEnum, HttpServletResponse response){
        Result<T> result = new Result<>();
        result.setSuccess(success);
        result.setTabType("2");
        result.setErrorCode(commonEnum.getCode());
        result.setErrorMsg(commonEnum.getMsg());
        result.setTraceId(MDC.get("X-B3-TraceId"));

        response(result, response);
    }




    public static void response(Object obj, ServletResponse response){
        PrintWriter writer = null;
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            writer = response.getWriter();
            writer.write(JSON.toJSONString(obj));
             writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != writer){
                writer.close();
            }
        }
    }

    /**
     * 数据转换为map
     * @param obj
     * @return
     */
    private static Map<Object, Object> conversion(Object obj){
        Map<Object, Object> dataMap = Maps.newConcurrentMap();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object o = field.get(obj);
                if(null == o){
                    continue;
                }
                dataMap.put(field.getName(), o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }

}
