package com.yn.electricity.vo;

import lombok.Data;

/**
 * @author admin
 * Date 2021/9/8 15:52
 * Description
 **/
@Data
public class PlatformVideoInVO {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 设备表主键
     */
    private Integer platformId;
    /**
     * 名称
     */
    private String name;
    /**
     * 通道编号
     */
    private Integer channelNo = 0;
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

    /**
     * 镜头播放url
     */
    private String rtspUrl;

    private String code;

    /**
     * 服务地址
     */
    private String serviceIp;

    /**
     * 服务端口
     */
    private Integer servicePort;


}
