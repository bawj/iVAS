package com.yn.electricity.request;

import com.yn.electricity.enums.MenuTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: MenuAlterRequest
 * @Author: zzs
 * @Date: 2021/2/24 9:51
 * @Description:
 */
@ApiModel
@Data
public class MenuAlterRequest implements Serializable {
    private static final long serialVersionUID = -4865619201286356563L;

    @ApiModelProperty(value = "主键id")
    @NotNull(message = "主键id不能为空")
    private Integer id;
    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String menuName;
    /**
     * 父Code
     */
    @ApiModelProperty(value = "父Code")
    private String parentCode;
    /**
     * 节点位置 提供枚举
     * {@link MenuTypeEnum}
     */
    @ApiModelProperty(value = "节点位置")
    private String menuType;
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
