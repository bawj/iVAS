package com.yn.electricity.result;

import com.yn.electricity.enums.UserLockStatusEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserResult
 * @Author: zzs
 * @Date: 2021/2/23 17:26
 * @Description: 返回用户信息列表
 */
@Data
public class UserResult implements Serializable {
    private static final long serialVersionUID = -2952838484551235753L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 日期到秒+应用id+6位随机数
     */
    private String ipId;
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
    private String email;
    /**
     * 邮箱登陆密码
     */
    private String eMailPassword;
    /**
     * 企业code
     */
    private String companyCode;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 角色id
     */
    private List<Integer> roleIdList;
    /**
     *  部门code
     */
    private String depCode;
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
     *  部门名称
     */
    private String depName;
    /**
     * 部门父级
     */
    private String depParentCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 角色权限
     */
    private List<Map<String, Object>> mapList;
}
