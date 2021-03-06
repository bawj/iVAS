package com.yn.electricity.vo;

import lombok.Data;

/**
 * @author admin
 * Date 2021/4/12 11:32
 * Description 镜头
 **/
@Data
public class CameraVO {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 设备id
     */
    private Integer devId;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态（0下线,1上线）
     */
    private Integer isOnline;
    /**
     * 通道
     */
    private Integer channelNo;

    /**
     * 注册IP
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    /**
     * 标记
     */
    private static String flag = "camera";

}
