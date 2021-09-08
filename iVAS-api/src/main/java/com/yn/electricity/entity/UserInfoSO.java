package com.yn.electricity.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserInfo
 * @Author: zzs
 * @Date: 2021/1/29 17:18
 * @Description:
 */

public class UserInfoSO implements Serializable {
    private static final long serialVersionUID = 7655181122178792879L;

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
     * 密码
     */
    private String password;
    /**
     * 盐 密码加密用
     */
    private String salt;
    /**
     * 角色及权限
     */
    private List<RoleMenuSO> roleMenuVoList;

    /**
     * 返回用户名加盐
     * @return
     */
    public String getCredentialsSalt(){
        return userName + salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpId() {
        return ipId;
    }

    public void setIpId(String ipId) {
        this.ipId = ipId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getParentIpId() {
        return parentIpId;
    }

    public void setParentIpId(String parentIpId) {
        this.parentIpId = parentIpId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<RoleMenuSO> getRoleMenuVoList() {
        return roleMenuVoList;
    }

    public void setRoleMenuVoList(List<RoleMenuSO> roleMenuVoList) {
        this.roleMenuVoList = roleMenuVoList;
    }
}
