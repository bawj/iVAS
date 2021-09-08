package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: DictDetailSaveRequest
 * @Author: zzs
 * @Date: 2021/3/17 16:16
 * @Description: 字典详情
 */
@Data
@ApiModel
public class DictDetailSaveRequest implements Serializable {
    private static final long serialVersionUID = -3304743236126974172L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    @NotBlank(message = "值不能为空")
    private String code;
    /**
     * 所属字典类型
     */
    @ApiModelProperty(value = "所属字典类型")
    @NotBlank(message = "所属字典类型不能为空")
    private String dictType;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @NotBlank(message = "是否启用不能为空")
    private String available;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
