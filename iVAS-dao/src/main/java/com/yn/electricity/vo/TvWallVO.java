package com.yn.electricity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 * Date 2021/3/22 9:49
 * Description 电视墙配置信息查询
 **/
@Data
@ApiModel(value = "配置信息查询")
public class TvWallVO {

    private Integer id;

    @ApiModelProperty(value = "配置名称")
    private String name;

    @ApiModelProperty(value = "轮询时间 单位s")
    private Integer pollTime;

    @ApiModelProperty(value = "窗口分割")
    private String windowDivision;

    @ApiModelProperty(value = "资源数量")
    private Integer resourceNum;

    @ApiModelProperty(value = "是否禁用 1启用 0禁用")
    private Boolean status;

    @ApiModelProperty(value = "备注说明")
    private String remarks;

    @ApiModelProperty(value = "资源来源")
    private Integer resourcesSource;

}
