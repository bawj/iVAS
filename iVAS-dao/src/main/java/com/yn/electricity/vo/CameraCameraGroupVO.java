package com.yn.electricity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CameraCameraGroupVO
 * @Author: zzs
 * @Date: 2021/4/13 11:05
 * @Description:
 */
@Data
public class CameraCameraGroupVO {
    /**
     * 镜头分组
     */
    private List<CameraGroupResVO> groupResVOList = new ArrayList<>();

    /**
     * 镜头集合
     */
    private List<CameraGroupRestVO> cameraList = new ArrayList<>();


}
