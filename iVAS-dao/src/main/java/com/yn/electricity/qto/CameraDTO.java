package com.yn.electricity.qto;

import lombok.Data;

import java.util.Date;

/**
 * @author admin
 * Date 2021/6/8 10:24
 * Description
 **/
@Data
public class CameraDTO {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 设备表主键
     */
    private Integer devId;

    private String ip;

    private Integer port;

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
    private double latitude;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 状态（0下线,1上线）
     */
    private Integer isOnline;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 镜头播放url
     */
    private String rtspUrl;

    /**
     * 是否有报警信息 0 没有 大于0 有
     */
    private Integer isAlarm = 0;
}
