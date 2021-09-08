package com.yn.electricity.qto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: DictDetailDTO
 * @Author: zzs
 * @Date: 2021/3/17 16:35
 * @Description: 字典详情查询入参类
 */
@Data
@ApiModel
public class DictDetailDTO extends BaseQuery{
    private static final long serialVersionUID = 5009330246289040765L;

    /**
     * 所属字典类型
     */
    @ApiModelProperty(value = "所属字典类型")
    private String dictType;

}
