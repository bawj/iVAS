package com.yn.electricity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/6/3 16:59
 * Description 根据id查询报警配置
 **/
@Data
public class AlarmConfigurationOneVO {

    private Integer id;

    @ApiModelProperty(value = "配置名称")
    private String name;

    @ApiModelProperty(value = "配置类型id")
    private Integer configurationTypeId;

    @ApiModelProperty(value = "配置类型名称")
    private String configurationTypeName;

    @ApiModelProperty(value = "布防时间")
    private String distributionTime;

    @ApiModelProperty(value = "关联资源")
    private List<AcOrganizationVO> organizationIds;

    @ApiModelProperty(value = "报警等级 0一般 1重要 2紧急 3非常紧急")
    private Integer alarmGrade;

    @ApiModelProperty(value = "响应类型id")
    private List<ResponseTypeVO> responseIds;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否启用 true启用 false禁用")
    private Boolean enable;



}
