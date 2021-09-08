package com.yn.electricity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 * Date 2021/5/24 11:22
 * Description 查询报警配置返回
 **/
@Data
@ApiModel(value = "查询报警配置返回")
public class AlarmConfigurationVO {

    private Integer id;

    @ApiModelProperty(value = "配置名称")
    private String name;

    @ApiModelProperty(value = "配置类型名称")
    private String configurationTypeName;

    @ApiModelProperty(value = "响应类型名称")
    private String responseName;

    @ApiModelProperty(value = "报警等级 0一般 1重要 2紧急 3非常紧急")
    private Integer alarmGrade;

    @ApiModelProperty(value = "布防时间")
    private String distributionTime;

    @ApiModelProperty(value = "关联资源数量")
    private Integer resourcesNumber;

    @ApiModelProperty(value = "是否启用 true启用 false禁用")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remarks;
}
