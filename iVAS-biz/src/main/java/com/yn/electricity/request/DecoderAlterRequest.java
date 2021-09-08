package com.yn.electricity.request;

import com.yn.electricity.qto.PassagewayDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 * Date 2021/6/15 15:57
 * Description
 **/
@Data
@ApiModel(value = "修改解码器参数")
public class DecoderAlterRequest {

    @NotNull(message = "id 不能为空")
    private Integer id;

    @NotEmpty(message = "解码器名称不能为空")
    @ApiModelProperty(value = "解码器名称")
    private String decoderName;

    @NotNull(message = "请选择接入类型")
    @ApiModelProperty(value = "接入类型id")
    private Integer deviceTypeId;

    @NotEmpty(message = "请输入ip")
    @ApiModelProperty(value = "注册IP")
    private String ip;

    @NotNull(message = "请输入端口")
    @ApiModelProperty(value = "端口")
    private Integer port;

    @NotEmpty(message = "请输入注册账号")
    @ApiModelProperty(value = "注册账号")
    private String registerAccount;

    @NotEmpty(message = "请输入注册密码")
    @ApiModelProperty(value = "注册密码")
    private String registerPassword;

    @ApiModelProperty(value = "统一编码")
    private String decoderCode;

    @NotNull(message = "请选中通道")
    @ApiModelProperty(value = "通道信息")
    private List<PassagewayDTO> passagewayList;
}
