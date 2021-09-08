package com.yn.electricity.service;

/**
 * @author redis缓存更新
 */
public interface RedisInfoService {

    /**
     * 更新预加载的设备缓存信息
     * @param devId devId
     */
    void redisUpdatePreloadingDevice(Integer devId);

    /**
     * 预加载缓存的设备信息
     */
    void redisPreloadingDevice();

    /**
     * 删除预加载的设备信息
     * @param devId devId
     */
    void delPreloadIngDevice(Integer devId);
}
