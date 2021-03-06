package com.yn.electricity.vo;

import lombok.Data;

/**
 * @ClassName: CameraGroupResVO
 * @Author: zzs
 * @Date: 2021/4/13 10:29
 * @Description: 返回镜头分组及镜头
 */
@Data
public class CameraGroupResVO {
    /**
     * 分组id
     */
    private Integer id;

    /**
     * 镜头组名称
     */
    private String name;

    /**
     * 在线数量
     */
    private Integer onlineQuantity;

    /**
     * 离线数量
     */
    private Integer offlineQuantity;

    /**
     * 标记
     */
    private static String flag = "cameraGroup";
}
