package com.yn.electricity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * Date 2021/3/17 14:32
 * Description 修改设备信息
 **/
@Data
@ApiModel(value = "修改设备信息")
public class DeviceAlterRequest {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id 不能为空")
    private Integer id;

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

    @ApiModelProperty(value = "所属镜头组id")
    private Integer cameraGroupId;

    @ApiModelProperty(value = "注册IP")
    @NotEmpty(message = "注册IP 不能为空")
    private String ip;

    @ApiModelProperty(value = "端口")
    @NotEmpty(message = "端口 不能为空")
    private String port;

    @ApiModelProperty(value = "注册账号")
    @NotEmpty(message = "注册id 不能为空")
    private String registerAccount;

    @ApiModelProperty(value = "注册密码")
    @NotEmpty(message = "注册密码 不能为空")
    private String registerPassword;

    @ApiModelProperty(value = "国标编码")
    private String code;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

}
