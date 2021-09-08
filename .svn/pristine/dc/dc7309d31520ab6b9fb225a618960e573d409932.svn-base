package com.yn.electricity.web;

import java.util.Map;

/**
 * @ClassName: Result
 * @Author: zzs
 * @Date: 2021/1/19 17:38
 * @Description:
 */
public class Result<T> {

    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 跳转页面
     */
    private String tabType = "0";
    /**
     * 数据
     */
    private T data;
    /**
     * 返回错误code
     */
    private String errorCode;
    /**
     * 返回错误内容
     */
    private String errorMsg;
    /**
     * 跟踪id
     */
    private String traceId;
    /**
     * 封装数据
     */
    private Map<Object, Object> dataMap;

    public Result() {
    }



    public Result(T data) {
        this.data = data;
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(String tabType, T data) {
        this.tabType = tabType;
        this.data = data;
    }

    public Result(boolean success, String tabType, T data) {
        this.success = success;
        this.tabType = tabType;
        this.data = data;
    }

    public Result(boolean success, String tabType, Map<Object, Object> dataMap) {
        this.success = success;
        this.tabType = tabType;
        this.dataMap = dataMap;
    }

    public Result(boolean success, String tabType, String errorCode, String errorMsg) {
        this.success = success;
        this.tabType = tabType;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Result(boolean success, String tabType, T data, String errorCode, String errorMsg, String traceId, Map<Object, Object> dataMap) {
        this.success = success;
        this.tabType = tabType;
        this.data = data;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.traceId = traceId;
        this.dataMap = dataMap;
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Map<Object, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<Object, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
