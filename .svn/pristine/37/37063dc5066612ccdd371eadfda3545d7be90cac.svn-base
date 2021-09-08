package com.yn.electricity.qto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: LogInfoDTO
 * @Author: zzs
 * @Date: 2021/3/18 9:34
 * @Description:
 */
@Data
@ApiModel
public class LogInfoDTO extends BaseQuery{
    private static final long serialVersionUID = -7457677028635128997L;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String operateName;
    /**
     * 内容
     */
    private String description;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "日志id 使用逗号隔开 \"1,2,3\"")
    private String idList;
}
