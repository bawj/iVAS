package com.yn.electricity.result;

import com.yn.electricity.enums.YesOrNotEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: RoleResult
 * @Author: zzs
 * @Date: 2021/2/24 9:43
 * @Description:
 */
@Data
public class RoleResult implements Serializable {
    /**
     * 自增
     */
    private Integer id;
    /**
     * ipRoleId 唯一
     */
    private String ipRoleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 是否可用  提供枚举： {@link YesOrNotEnum}
     */
    private String available;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 描述
     */
    private String description;
}
