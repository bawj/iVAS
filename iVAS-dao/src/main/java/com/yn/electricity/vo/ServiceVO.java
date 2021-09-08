package com.yn.electricity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 * Date 2021/3/16 15:28
 * Description 返回接入服务列表
 **/
@Data
@ApiModel(value = "接入服务列表")
public class ServiceVO {

    /**
     * 主键
     */
    private Integer id;

    @ApiModelProperty(value = "服务名称")
    private String name;

    @ApiModelProperty(value = "pas 接入服务 vas分析服务")
    private String type;

    @ApiModelProperty(value = "服务地址")
    private String ip;

    @ApiModelProperty(value = "服务端口")
    private Integer port;

    @ApiModelProperty(value = "接入的设备类型")
    private String vendor;

    @ApiModelProperty(value = "负载值")
    private Double payload;

    @ApiModelProperty(value = "状态（0下线,1上线）")
    private Short online;

}
