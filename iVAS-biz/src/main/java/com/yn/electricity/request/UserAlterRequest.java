package com.yn.electricity.request;

import com.yn.electricity.enums.UserLockStatusEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserRequest
 * @Author: zzs
 * @Date: 2021/2/23 14:21
 * @Description: 修改用户
 */
@ApiModel
@Data
public class UserAlterRequest implements Serializable {
    private static final long serialVersionUID = 8365894876875608721L;

    @ApiModelProperty(value = "主键id")
    @NotNull(message = "主键不能为空")
    private Integer id;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 男，女
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 应用id
     */
    private String appId;
    /**
     *  企业编码
     */
    @ApiModelProperty(value = "企业编码")
    private String companyCode;
    /**
     * 父类ipId
     */
    @ApiModelProperty(value = "父类ipId")
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
     * 锁状态 提供枚举： {@link UserLockStatusEnum}
     */
    @ApiModelProperty(value = "锁状态")
    private String lockStatus;
    /**
     * 逻辑删除 提供枚举： {@link YesOrNotEnum}
     */
    @ApiModelProperty(value = "是否删除")
    private String isDelete;
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
