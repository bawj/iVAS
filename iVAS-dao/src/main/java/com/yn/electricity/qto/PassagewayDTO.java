package com.yn.electricity.qto;

import com.yn.electricity.vo.DecoderPassagewayCameraVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 * Date 2021/6/15 16:49
 * Description
 **/
@ApiModel(value = "增加解码器 通道信息")
@Data
public class PassagewayDTO {

    @NotEmpty(message = "通道名称不能为空")
    @ApiModelProperty(value = "通道名称")
    private String passagewayName;

    @NotNull(message = "通道号不能为空")
    @ApiModelProperty(value = "通道号")
    private Integer channelNo;

    @NotNull(message = "通道状态不能为空")
    @ApiModelProperty(value = "状态")
    private Integer status;

    @NotNull(message = "请选择电视墙配置")
    @ApiModelProperty(value = "电视墙配置id")
    private Integer tvWallId;

    @NotNull(message = "请选择镜头")
    @ApiModelProperty(value = "镜头id")
    private List<DecoderPassagewayCameraVO> cameraIdList;

}
