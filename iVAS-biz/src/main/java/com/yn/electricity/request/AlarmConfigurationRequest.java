package com.yn.electricity.request;

import com.yn.electricity.entity.DistributionTimeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 * Date 2021/5/21 9:42
 * Description 报警配置添加
 **/
@Data
@ApiModel(value = "报警配置")
public class AlarmConfigurationRequest{

    @NotEmpty(message = "配置名称不能为空")
    @ApiModelProperty(value = "配置名称")
    private String name;

    @NotNull(message = "请选择配置类型")
    @ApiModelProperty(value = "配置类型id")
    private Integer configurationTypeId;

    @NotNull(message = "请选择布防时间")
    @ApiModelProperty(value = "布防时间 结束时间大于开始时间")
    private DistributionTimeEntity distributionTime;

    @ApiModelProperty(value = "组织机构id")
    private List<Integer> organizationIds;

    @NotNull(message = "请选择响应类型")
    @ApiModelProperty(value = "响应类型id")
    private List<Integer> responseTypeIds;

    @Max(value = 3,message = "报警等级 错误")
    @Min(value = 0,message = "报警等级 错误")
    @NotNull(message = "请选择报警等级")
    @ApiModelProperty(value = "报警等级 0一般 1重要 2紧急 3非常紧急")
    private Integer alarmGrade;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @NotNull(message = "请选择是否启用")
    @ApiModelProperty(value = "是否启用 0 禁用 1启用")
    private Boolean enable;
}

