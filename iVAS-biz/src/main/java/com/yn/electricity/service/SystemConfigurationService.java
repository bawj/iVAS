package com.yn.electricity.service;

import com.yn.electricity.vo.SystemConfigurationVO;

/**
 * @author 时间同步
 */
public interface SystemConfigurationService {

    String deviceTimeSynchronization(String syncDate);

    String deviceAutomaticSynchronization(Boolean isOpen ,Integer interval);

    /**
     * 是否开启同步
     * @param isOpen isOpen
     * @param interval 同步时间单位天
     */
    void startSync(Boolean isOpen , Integer interval);

    /**
     * 查询是否开启了同步状态
     * @return SystemConfigurationVO
     */
    SystemConfigurationVO deviceFindAutomatic();

}
