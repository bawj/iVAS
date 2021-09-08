package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 * Date 2021/3/16 10:07
 * Description 修改镜头组参数
 **/
@Data
@ApiModel(value = "修改镜头组参数")
public class CameraGroupAlterRequest {

    private Integer id;

    /**
     * 镜头组名称
     */
    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "镜头组名称")
    private String name;

    /**
     * 排序
     */
    @NotNull(message = "sport 不能为空")
    @ApiModelProperty(value = "排序")
    private Integer sport;

    /**
     * 上级id
     */
    @NotNull(message = "cameraGroupId 不能为空")
    @ApiModelProperty(value = "上级id")
    private Integer cameraGroupId;

    /**
     * 镜头id
     */
    @ApiModelProperty(value = "镜头id")
    private List<Integer> cameraIdList;
}
