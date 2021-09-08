package com.yn.electricity.service;

import com.yn.electricity.pas.DeviceAlarmRequest;

/**
 * @author pas上报报警信息
 */
public interface AlarmInfoService {

    /**
     * pas上报报警信息
     * @param pasBaseRequest pasBaseRequest
     * @return String
     */
    String deviceAlarmInfo(DeviceAlarmRequest pasBaseRequest);

}
