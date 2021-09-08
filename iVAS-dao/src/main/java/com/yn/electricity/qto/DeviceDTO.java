package com.yn.electricity.qto;

import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/6/1 11:35
 * Description 设备信息
 **/
@Data
public class DeviceDTO {

    /**
     * device主键
     */
    private Integer id;

    /**
     * 设备名称
     */
    private String devName;

    /**
     * 经度
     */
    private Double longitude = 0D;

    /**
     * 状态（0下线,1上线）
     */
    private Integer isOnline;

    private String ip;

    private Integer port;

    /**
     * 纬度
     */
    private Double latitude = 0D;

    private String companyCode;

    private String departmentCode;


    /**
     * 是否有报警 1 有 0 没有
     */
    private Integer isAlarm;

    private List<CameraDTO> cameraList;
}

