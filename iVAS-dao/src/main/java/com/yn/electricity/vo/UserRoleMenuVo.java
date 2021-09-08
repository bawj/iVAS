package com.yn.electricity.vo;

import com.yn.electricity.enums.UserLockStatusEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserRoleMenuVo
 * @Author: zzs
 * @Date: 2021/2/25 10:36
 * @Description:
 */
@Data
public class UserRoleMenuVo implements Serializable {
    private static final long serialVersionUID = 4686119614751925757L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 登陆账号手机号或邮箱
     */
    private String userName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String eMail;
    /**
     * 邮箱登陆密码
     */
    private String eMailPassword;
    /**
     * 手机号登陆密码
     */
    private String password;
    /**
     * 企业code
     */
    private String companyCode;
    /**
     * 日期到秒+应用id+6位随机数
     */
    private String ipId;
    /**
     *  部门code
     */
    private String depCode;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 是否可用  提供枚举： {@link YesOrNotEnum}
     */
    private String available;
    /**
     * 锁状态 提供枚举： {@link UserLockStatusEnum}
     */
    private String lockStatus;
    /**
     * 父类ipId
     */
    private String parentIpId;
    /**
     * 盐 密码加密用
     */
    private String salt;
    /**
     * 角色及权限
     */
    private List<RoleMenuVo> roleMenuVoList;

    /**
     * 返回用户名加盐
     * @return
     */
    public String getCredentialsSalt(String password){
        return password + salt;
    }
}
