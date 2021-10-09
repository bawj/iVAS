package com.yn.electricity.service.pas;

import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.dao.ServiceDAO;
import com.yn.electricity.dao.VideoPlanDAO;
import com.yn.electricity.pas.PasBaseRequest;
import com.yn.electricity.pas.PasDelVideoPlanInfo;
import com.yn.electricity.qto.VideoPlanDTO;

import java.util.List;

/**
 * @author pas 设备相关service
 */
public interface PasDeviceService {

    /**
     * 添加设备到pas
     *
     * @param deviceDAO device
     * @return int
     */
    Integer sendAddDevicePas(DeviceDAO deviceDAO);

    /**
     * 添加国标平台 pas
     * @param deviceDAO deviceDAO
     * @return Integer
     */
    Integer sendAddPlatformPas(DeviceDAO deviceDAO);

    /**
     * 删除设备pas
     *
     * @param ids 设备表 device  主键
     * @return Integer
     */
    Integer sendDelDeviceService(List<Integer> ids);


    /**
     * 发送到pas
     *
     * @param videoPlanDTO videoPlan
     */
    void sendVideoPlan(VideoPlanDTO videoPlanDTO);

    /**
     * 删除录像计划发送到pas
     *
     * @param serviceDAO serviceDAO
     * @param ids        ids
     */
    void sendDelVideoPlan(ServiceDAO serviceDAO, List<Integer> ids);

}
