package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author admin
 * Date 2021/3/15 16:21
 * Description 组织架构
 **/
@Data
@ApiModel(value = "增加组织架构参数")
public class OrganizationSaveRequest {

    @ApiModelProperty(value = "组织名称")
    @NotNull(message = "组织名称 不能为空")
    private String name;

    @ApiModelProperty(value = "上级id")
    @NotNull(message = "organizationId 不能为空")
    private Integer organizationId;

    @NotNull(message = "depCode 不能为空")
    private String depCode;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "sport 不能为空")
    private Integer sport;

}
