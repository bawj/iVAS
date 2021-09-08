package com.yn.electricity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 * Date 2021/3/16 18:05
 * Description 返回前端设备类型
 **/
@Data
@ApiModel(value = "设备类型信息")
public class DeviceTypeVO {

    private Integer id;

    @ApiModelProperty(value = "类型名称")
    private String name;

}
