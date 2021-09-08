package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName: DictSaveRequest
 * @Author: zzs
 * @Date: 2021/3/17 16:12
 * @Description: 新增字典类型对象
 */
@Data
@ApiModel
public class DictSaveRequest implements Serializable {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
}
