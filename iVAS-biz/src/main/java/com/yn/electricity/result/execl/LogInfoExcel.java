package com.yn.electricity.result.execl;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: LogInfoExecl
 * @Author: zzs
 * @Date: 2021/4/7 10:14
 * @Description: 日志下载实体类
 */
@Data
@Excel
public class LogInfoExcel implements Serializable {
    private static final long serialVersionUID = -9064841932900533409L;

    @ExcelField("操作者ip")
    private String ip;
    @ExcelField("操作名称")
    private String menuName;
    @ExcelField("操作人名称")
    private String operateName;
    @ExcelField("描述")
    private String description;
    @ExcelField(value = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
