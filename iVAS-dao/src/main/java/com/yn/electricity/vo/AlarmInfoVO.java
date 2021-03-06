package com.yn.electricity.vo;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author admin
 * Date 2021/5/31 11:01
 * Description 报警信息
 **/
@Data
@Excel
public class AlarmInfoVO {

    private Integer id;

    private Integer cameraId;

    /**
     * 报警时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelField(value = "报警时间")
    private String alarmTime;

    /**
     * 报警对象
     */
    @ExcelField(value = "报警对象")
    private String cameraName;

    /**
     * 所属组织名称
     */
    @ExcelField(value = "所属组织")
    private String organizationName;

    /**
     * 报警等级 0一般 1重要 2紧急 3非常紧急
     */
    private Integer alarmGrade;

    /**
     * 报警配置类型 alarm_configuration_type 表id
     */
    private String typeName;

    /**
     * 报警内容
     */
    private String alarmContent;

    /**
     * 确认状态 0未确认 1已确认
     */
    private Boolean confirmStatus;

    /**
     * 确认人 名称
     */
    private String confirmPeople;

    /**
     * 确认时间
     */
    private Date confirmTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 报警抓图
     */
    private String alarmSnapshotUrl;

    /**
     * 是否播放报警提示音
     */
    private Boolean isPlayTone;
}
