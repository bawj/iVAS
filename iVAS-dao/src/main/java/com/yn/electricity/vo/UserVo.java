package com.yn.electricity.vo;


import lombok.Data;

import java.util.*;


/**
 * @ClassName: UserVo
 * @Author: zzs
 * @Date: 2021/3/11 14:47
 * @Description: 用户列表查询
 */
@Data
public class UserVo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 内容不限
     */
    private String userName;
    /**
     * 男，女
     */
    private String sex;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String eMail;
    /**
     * 邮箱登陆密码(由后台加密生成)
     */
    private String eMailPassword;
    /**
     * 密码
     */
    private String password;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 角色id
     */
    private List<Integer> roleIdList;

    private Integer roleId;
    /**
     *  企业编码
     */
    private String companyCode;
    /**
     * 日期到秒+应用id+6位随机数
     */
    private String ipId;
    /**
     * 部门编号
     */
    private String dep_code;
    /**
     * 是否可用  提供枚举： {@link YesOrNotEnum}
     */
    private String available;
    /**
     * 锁状态 提供枚举： {@link UserLockStatusEnum}
     */
    private String lockStatus;
    /**
     * 盐 密码加密用
     */
    private String salt;
    /**
     * 父类ipId
     */
    private String parentIpId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 逻辑删除 提供枚举： @link YesOrNotEnum
     */
    private String isDelete;
    /**
     *  部门名称
     */
    private String depName;
    /**
     * 部门编码
     */
    private String depCode;
    /**
     * 部门父级
     */
    private String depParentCode;
}
