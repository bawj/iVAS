package com.yn.electricity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 * Date 2021/5/20 15:47
 * Description
 **/
@Data
@ApiModel(value = "布放时间")
public class DistributionTimeEntity {

    @ApiModelProperty(value = "周一")
    private DisTime monday;
    @ApiModelProperty(value = "周二")
    private DisTime tuesday;
    @ApiModelProperty(value = "周三")
    private DisTime wednesday;
    @ApiModelProperty(value = "周四")
    private DisTime thursday;
    @ApiModelProperty(value = "周五")
    private DisTime friday;
    @ApiModelProperty(value = "周六")
    private DisTime saturday;
    @ApiModelProperty(value = "周日")
    private DisTime sunday;

    @Data
    @ApiModel(value = "开始时间和结束时间")
    public static class DisTime {
        @ApiModelProperty(value = "开始时间")
        private String startTime;
        @ApiModelProperty(value = "结束时间")
        private String endTime;
    }
}



