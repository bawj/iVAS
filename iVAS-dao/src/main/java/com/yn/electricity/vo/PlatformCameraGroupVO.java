package com.yn.electricity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/9/8 15:40
 * Description
 **/
@Data
public class PlatformCameraGroupVO {

    private List<PlatformGroupVO> platformGroupList;

    private List<PlatformVideoInVO> platformCameraList;
}
