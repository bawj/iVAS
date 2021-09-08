package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: DepartmentSaveRequest
 * @Author: zzs
 * @Date: 2021/3/12 9:40
 * @Description: 新增部门对象
 */
@ApiModel
@Data
public class DepartmentAlterRequest implements Serializable {
    private static final long serialVersionUID = 3664902002232043883L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @NotNull
    private Integer id;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;
    /**
     * 父级code
     */
    @ApiModelProperty(value = "父级code")
    private String parentCode;
    /**
     * 是否启用，y是n否
     */
    @ApiModelProperty(value = "是否启用")
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
    @Min(value = 0)
    private String sort;
}
