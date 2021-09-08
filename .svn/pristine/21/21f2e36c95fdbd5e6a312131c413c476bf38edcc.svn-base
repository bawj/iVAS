package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName: DepartmentSaveRequest
 * @Author: zzs
 * @Date: 2021/3/12 9:40
 * @Description: 新增部门对象
 */
@ApiModel
@Data
public class DepartmentSaveRequest implements Serializable {
    private static final long serialVersionUID = 3664902002232043883L;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 父级code
     */
    @ApiModelProperty(value = "父级code")
    @NotBlank(message = "父级code不能为空")
    private String parentCode;
    /**
     * 是否启用，y是n否
     */
    @ApiModelProperty(value = "是否启用")
    @NotBlank(message = "是否启用不能为空")
    private String available;
    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private Integer level;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @NotBlank(message = "排序不能为空")
    @Min(value = 0)
    private String sort;
}
