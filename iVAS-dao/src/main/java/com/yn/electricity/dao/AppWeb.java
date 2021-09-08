package com.yn.electricity.dao;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: AppWeb
 * @Author: zzs
 * @Date: 2021/2/22 15:43
 * @Description:
 */
@Data
public class AppWeb {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 应用中文名称
     */
    private String appName;

    /**
     * 应用英文名称
     */
    private String app;

    /**
     * 是否可用 提供枚举 @link YesOrNotEnum
     */
    private String available;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

}
