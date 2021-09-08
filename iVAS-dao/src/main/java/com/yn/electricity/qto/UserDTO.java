package com.yn.electricity.qto;

import com.yn.electricity.enums.YesOrNotEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserDTO
 * @Author: zzs
 * @Date: 2021/2/23 14:14
 * @Description:
 */
@ApiModel
@Data
public class UserDTO extends BaseQuery{
    private static final long serialVersionUID = 8887167381016498703L;
    /**
     * 公司编码
     */
    private String companyCode;
    /**
     * 部门编码
     */
    @ApiModelProperty(value = "部门编码")
    private String depCode;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    private String eMail;
    /**
     * 是否启用  提供枚举： {@link YesOrNotEnum}
     */
    @ApiModelProperty(value = "是否启用")
    private String available;
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
    @ApiModelProperty(value = "用户id使用逗号隔开 \"1,2,3\"")
    private String idList;

}
