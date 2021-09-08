package com.yn.electricity.service;

/**
 * @author admin
 * Date 2021/9/3 14:11
 * Description 设备和平台公用的方法
 **/
public interface DevicePlatformUtilService {


    /**
     * 查询 当前的service 是否和type 一致
     * @param serviceId serviceId
     * @param type type
     */
    void serviceType(Integer serviceId , String type);
}
