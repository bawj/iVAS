package com.yn.electricity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yn.electricity.enums.UserLockStatusEnum;
import com.yn.electricity.enums.YesOrNotEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Author: zzs
 * @Date: 2021/1/18 14:10
 * @Description: 用户信息实体类
 */
@Data
@TableName("user")
public class User implements Serializable {

    public static final long serialVersionUID = -1658843262899495770L;

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
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
     * 返回用户名加盐
     * @return
     */
    public String getCredentialsSalt(){
        return userName + salt;
    }

}
