package com.yn.electricity.result.execl;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import com.yn.electricity.enums.UserLockStatusEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserDowloadResult
 * @Author: zzs
 * @Date: 2021/4/6 17:29
 * @Description: 用户下载实体类
 */
@Data
@Excel(value = "用户列表下载")
public class UserDowExcel implements Serializable {
    private static final long serialVersionUID = -8994650881076241053L;

    @ExcelField("昵称")
    private String nickname;
    @ExcelField("用户名")
    private String userName;
    @ExcelField("性别")
    private String sex;
    @ExcelField("手机号")
    private String phone;
    @ExcelField("邮箱")
    private String email;
    /**
     * 是否可用  提供枚举： {@link YesOrNotEnum}
     */
    @ExcelField("是否可用")
    private String available;
    /**
     * 锁状态 提供枚举： {@link UserLockStatusEnum}
     */
    @ExcelField("锁状态")
    private String lockStatus;
    @ExcelField("部门名称")
    private String depName;
    @ExcelField(value = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
