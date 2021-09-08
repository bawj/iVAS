package com.yn.electricity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author admin
 * Date 2021/7/28 14:53
 * Description
 **/
@Data
public class CameraGroupRestVO {

    /**
     * 主键
     */
    private Integer id;

    private String ip;

    private Integer port;

    /**
     * 设备表主键
     */
    private Integer devId;
    /**
     * 名称
     */
    private String name;
    /**
     * 通道编号
     */
    private Integer channelNo;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 状态（0下线,1上线）
     */
    private Integer isOnline;


}
