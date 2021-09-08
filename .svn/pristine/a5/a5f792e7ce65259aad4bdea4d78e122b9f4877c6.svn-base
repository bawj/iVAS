package com.yn.electricity.request;

import com.yn.electricity.enums.YesOrNotEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName: RoleSaveRequest
 * @Author: zzs
 * @Date: 2021/2/24 9:28
 * @Description:
 */
@ApiModel
@Data
public class RoleSaveRequest implements Serializable {
    private static final long serialVersionUID = 499661411808246511L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**
     * 是否启用  提供枚举： {@link YesOrNotEnum}
     */
    @ApiModelProperty(value = "是否启用")
    private String available;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 权限id 多个以逗号分开
     */
    @ApiModelProperty(value = "权限id 多个以逗号分开")
    private String menuId;
    /**
     *  数据类型
     *  0 全部数据权限
     *  1 本部门数据权限
     *  2 自定义数据权限
     */
    @ApiModelProperty(value = "数据类型")
    private String dataType;
    /**
     * 部门Code (注释：暂时数据权限只到部门，以后可能扩展到具体字段)
     */
    @ApiModelProperty(value = "部门Code")
    private String depCode;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
}
