package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author admin
 * Date 2021/3/15 18:07
 * Description 修改组织机构
 **/
@Data
@ApiModel(value = "修改组织机构参数")
public class OrganizationAlterRequest {

    @NotNull(message = "id 不能为空")
    private Integer id;

    @ApiModelProperty(value = "组织名称")
    @NotNull(message = "组织名称 不能为空")
    private String name;

    @ApiModelProperty(value = "上级id")
    @NotNull(message = "organizationId 不能为空")
    private Integer organizationId;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "sport 不能为空")
    private Integer sport;
}
