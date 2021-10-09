package com.yn.electricity.request;

import com.yn.electricity.annotation.DateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 * Date 2021/8/2 17:30
 * Description
 **/
@Data
@ApiModel(value = "参数")
public class VideoPlanSaveRequest {

    @NotNull(message = "镜头id不能为空")
    @ApiModelProperty(value = "镜头id")
    private Integer cameraId;

    @ApiModelProperty(value = "录像计划详情信息")
    private List<VideoPlanInfo> videoPlanInfoList;

    @Data
   public static class VideoPlanInfo{

        @NotNull(message = "type 不能为空")
        @Max(value = 6 , message = "最大为6")
        @Min(value = 0 , message = "最小为0")
        @ApiModelProperty(value = "0 代表周1 、1代表周2 ... 6代表周日")
        private Integer type;

        @ApiModelProperty(value = "主辅码流 0主1辅")
        private Integer subType;

        @DateTime(format = "HH:mm:ss")
        @ApiModelProperty(value = "开始录像时间 格式 HH:mm:ss")
        private String beginTime;

        @DateTime(format = "HH:mm:ss")
        @ApiModelProperty(value = "结束录像时间 格式 HH:mm:ss")
        private String endTime;
    }

}
