package com.yn.electricity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/6/16 9:52
 * Description
 **/
@Data
public class PassagewayInfoVO {

    @ApiModelProperty(value = "通道id")
    private Integer passagewayId;

    @ApiModelProperty(value = "通道名称")
    private String passagewayName;

    @ApiModelProperty(value = "电视墙配置id")
    private Integer tvWallId;

    @ApiModelProperty(value = "电视墙配置名称")
    private String tvWallName;

    @ApiModelProperty(value = "镜头id")
    private List<DecoderPassagewayCameraVO> cameraIdList;
}
