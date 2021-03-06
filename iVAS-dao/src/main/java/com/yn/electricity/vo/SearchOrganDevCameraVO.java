package com.yn.electricity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 * Date 2021/6/25 17:14
 * Description
 **/
@Data
@ApiModel(value = "查询结果")
public class SearchOrganDevCameraVO {

    private Integer id;

    @ApiModelProperty(value = "1 组织机构 2 设备 3 镜头")
    private Integer type;

    @ApiModelProperty(value = "名称")
    private String name;

    private Integer devId;

}
