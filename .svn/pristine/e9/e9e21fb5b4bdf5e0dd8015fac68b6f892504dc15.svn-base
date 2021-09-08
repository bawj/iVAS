package com.yn.electricity.vo;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import lombok.Data;

import java.util.Date;

/**
 * @author admin
 * Date 2021/7/28 14:08
 * Description
 **/
@Data
@Excel
public class AlarmInfoExportVO {

    /**
     * 报警时间
     */
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
    @ExcelField(value = "报警等级")
    private String alarmGrade;

    /**
     * 报警配置类型 alarm_configuration_type 表id
     */
    @ExcelField(value = "报警类型")
    private String typeName;

    /**
     * 报警内容
     */
    @ExcelField(value = "报警内容")
    private String alarmContent;

    /**
     * 确认状态 0未确认 1已确认
     */
    @ExcelField(value = "确认状态")
    private String confirmStatus;

    /**
     * 确认人 名称
     */
    @ExcelField(value = "确认人")
    private String confirmPeople;

    /**
     * 确认时间
     */
    @ExcelField(value = "确认时间")
    private String confirmTime;

    /**
     * 备注
     */
    @ExcelField(value = "备注")
    private String remarks;

}
