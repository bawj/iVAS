package com.yn.electricity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: CameraGroupResult
 * @Author: zzs
 * @Date: 2021/3/26 15:36
 * @Description: 设备分组
 */
@Data
public class CameraGroupResultVO implements Serializable {
    private static final long serialVersionUID = 1775321713997246323L;
    /**
     * 分组id
     */
    private Integer id;

    /**
     * 镜头组名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sport;

    private List<CameraGroupResultVO> cameraGroupList;
}
