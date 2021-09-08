package com.yn.electricity.request;

import com.yn.electricity.enums.YesOrNotEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserRequest
 * @Author: zzs
 * @Date: 2021/2/23 14:21
 * @Description: 新增用户
 */
@ApiModel
@Data
public class UserSaveRequest implements Serializable {
    private static final long serialVersionUID = 8365894876875608721L;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /**
     * 男，女
     */
    @ApiModelProperty(value = "性别")
    @NotBlank(message = "性别不能为空")
    private String sex;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 应用id
     */
    private String appId="0";
    /**
     *  企业编码
     */
    @ApiModelProperty(value = "企业编码")
    @NotBlank(message = "企业编码不能为空")
    private String companyCode;
    /**
     * 父类ipId
     */
    @ApiModelProperty(value = "父类ipId")
    @NotBlank(message = "父级ipId不能为空，顶级账号传o")
    private String parentIpId;
    /**
     * 部门编号
     */
    @ApiModelProperty(value = "部门编号")
    private String depCode;
    /**
     * 是否启用  提供枚举： {@link YesOrNotEnum}
     */
    @ApiModelProperty(value = "是否启用")
    private String available;
    /**
     * 盐 密码加密用
     */
    private String salt;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

}
