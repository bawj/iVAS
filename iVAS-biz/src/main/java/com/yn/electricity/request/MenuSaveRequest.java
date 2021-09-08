package com.yn.electricity.request;

import com.yn.electricity.enums.MenuTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName: MenuSaveRequest
 * @Author: zzs
 * @Date: 2021/2/24 9:50
 * @Description:
 */
@ApiModel
@Data
public class MenuSaveRequest implements Serializable {
    private static final long serialVersionUID = -4918008504706495818L;

    private String code;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    @NotBlank(message = "权限名称不能为空")
    private String menuName;

    /**
     * 权限路由
     */
    @ApiModelProperty(value = "权限路由")
    @NotBlank(message = "权限路由不能为空")
    private String menuRoute;

    /**
     * 节点位置 提供枚举
     * {@link MenuTypeEnum}
     */
    @ApiModelProperty(value = "节点位置")
    private String menuType;

    private String routeType;

    /**
     * 父Code
     */
    @ApiModelProperty(value = "父Code")
    private String parentCode;

    /**
     * 是否启用，y是n否
     */
    @ApiModelProperty(value = "是否启用")
    private String available;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
