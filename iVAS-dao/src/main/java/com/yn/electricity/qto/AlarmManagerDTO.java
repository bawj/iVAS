package com.yn.electricity.qto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author admin
 * Date 2021/5/31 13:39
 * Description 报警管理查询条件
 **/
@Data
public class AlarmManagerDTO extends BaseQuery{

    /**
     * 摄像头名称
     */
    private String cameraName;

    /**
     * 组织id
     */
    private Integer organizationId;

    /**
     * 报警配置类型
     */
    private Integer alarmConfigurationTypeId;

    /**
     * 报警等级
     */
    private Integer alarmGrade;

    /**
     * 确认状态
     */
    private Boolean confirmStatus;

    /**
     * 报警开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date alarmTimeStart;

    /**
     * 报警结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date alarmTimeEnd;

}
