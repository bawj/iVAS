package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: DictSaveRequest
 * @Author: zzs
 * @Date: 2021/3/17 16:12
 * @Description: 修改字典类型对象
 */
@Data
@ApiModel
public class DictAlterRequest implements Serializable {
    private static final long serialVersionUID = 7740412654776440522L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "主键")
    private Integer id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;
}
