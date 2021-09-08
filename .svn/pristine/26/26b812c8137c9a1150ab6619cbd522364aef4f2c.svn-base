package com.yn.electricity.qto;

import com.yn.electricity.enums.YesOrNotEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: RoleDTO
 * @Author: zzs
 * @Date: 2021/2/24 9:41
 * @Description:
 */
@Data
@ApiModel
public class RoleDTO extends BaseQuery{
    private static final long serialVersionUID = 8002086147707672747L;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 是否启用  提供枚举： {@link YesOrNotEnum}
     */
    @ApiModelProperty(value = "是否启用")
    private String available;
}
