package com.yn.electricity.qto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/5/24 11:29
 * Description 报警配置查询条件
 **/
@Data
public class AlarmConfigurationDTO extends BaseQuery{

    @ApiModelProperty(value = "配置名称")
    private String name;

    @ApiModelProperty(value = "配置类型id")
    private Integer alarmConfigurationTypeId;

    @ApiModelProperty(value = "响应类型id")
    private Integer responseTypeId;

    @ApiModelProperty(value = "报警等级")
    private Integer alarmGrade;

    @ApiModelProperty(value = "是否启用 true启用 false禁用")
    private Boolean enable;

    private List<Integer> alarmConfigurationIds;
}
