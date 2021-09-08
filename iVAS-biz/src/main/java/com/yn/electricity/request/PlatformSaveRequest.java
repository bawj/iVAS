package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * Date 2021/3/17 10:23
 * Description 添加设备
 **/
@Data
@ApiModel(value = "添加设备信息")
public class PlatformSaveRequest {

    @ApiModelProperty(value = "设备名称")
    @NotEmpty(message = "设备名称不能为空")
    private String name;

    @ApiModelProperty(value = "设备类型 id")
    @NotNull(message = "请选择设备类型")
    private Integer deviceTypeId;

    @ApiModelProperty(value = "接入服务id")
    @NotNull(message = "请选择接入服务")
    private Integer serviceId;

    @ApiModelProperty(value = "所属组织id")
    private Integer organizationId;

    @ApiModelProperty(value = "注册IP")
    @NotEmpty(message = "注册IP 不能为空")
    private String ip;

    @ApiModelProperty(value = "端口")
    @NotNull(message = "端口 不能为空")
    private Integer port;

    @ApiModelProperty(value = "注册账号")
    @NotEmpty(message = "注册id 不能为空")
    private String registerAccount;

    @ApiModelProperty(value = "注册密码")
    @NotEmpty(message = "注册密码 不能为空")
    private String registerPassword;

    @ApiModelProperty(value = "国标编码")
    @NotEmpty(message = "国标编码 不能为空")
    private String code;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

}
