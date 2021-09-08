package com.yn.electricity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 * Date 2021/3/18 15:52
 * Description excel 读取的数据
 **/
@Data
public class DeviceExcelRequest {

    @ApiModelProperty(value = "设备名称")
    @NotEmpty(message = "设备名称不能为空")
    private String name;

    @ApiModelProperty(value = "设备类型")
    @NotNull(message = "设备类型不能为空")
    private String deviceType;

    @ApiModelProperty(value = "注册IP")
    @NotEmpty(message = "注册IP不能为空")
    private String ip;

    @ApiModelProperty(value = "端口")
    @NotNull(message = "端口不能为空")
    private Integer port;

    @ApiModelProperty(value = "注册账号")
    @NotEmpty(message = "注册id不能为空")
    private String registerAccount;

    @ApiModelProperty(value = "注册密码")
    @NotEmpty(message = "注册密码不能为空")
    private String registerPassword;

    @ApiModelProperty(value = "接入服务id")
    @NotEmpty(message = "接入服务不能为空")
    private String serviceName;

    @ApiModelProperty(value = "国标编码")
    private String code;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "所属组织id")
    @NotEmpty(message = "所属组织不能为空")
    private String organizationName;
}
