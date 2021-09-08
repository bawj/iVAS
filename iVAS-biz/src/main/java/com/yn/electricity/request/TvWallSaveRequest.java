package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 电视墙
 */
@Data
@ApiModel(value = "添加电视墙信息")
public class TvWallSaveRequest {

    @ApiModelProperty(value = "配置名称")
    @NotEmpty(message = "配置名称不能为空")
    private String name;

    @ApiModelProperty(value = "轮询时间")
    @NotNull(message = "轮询时间不能为空")
    @Min(value = 1 , message = "最小轮询时间为1")
    private Integer pollTime;

    @ApiModelProperty(value = "是否禁用 1启用 0禁用")
    @NotNull(message = "请选择是否启动")
    private Boolean status;

    @ApiModelProperty(value = "备注说明")
    private String remarks;

    @ApiModelProperty(value = "窗口分割")
    @NotNull(message = "请选择窗口分割")
    private String windowDivision;
}