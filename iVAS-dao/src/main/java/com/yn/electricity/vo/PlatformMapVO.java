package com.yn.electricity.vo;

import com.yn.electricity.qto.CameraDTO;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/6/1 11:35
 * Description 设备信息
 **/
@Data
public class PlatformMapVO {

    /**
     * device主键
     */
    private Integer id;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 经度
     */
    private Double longitude = 0D;

    /**
     * 纬度
     */
    private Double latitude = 0D;

    /**
     * 状态（0下线,1上线）
     */
    private Integer isOnline;

    private String code;

    private String ip;

    private Integer port;

    private List<PlatformVideoInVO> cameraList;
}

