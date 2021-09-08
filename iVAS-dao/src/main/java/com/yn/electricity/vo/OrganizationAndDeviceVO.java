package com.yn.electricity.vo;

import lombok.Data;

/**
 * @author admin
 * Date 2021/7/1 14:28
 * Description
 **/
@Data
public class OrganizationAndDeviceVO {

    private Integer id;

    /**
     * 组织名称
     */
    private String name;

    private Integer organizationId;

    private Integer sport;

    /**
     * 在线数量
     */
    private Integer onlineQuantity;

    /**
     * 离线数量
     */
    private Integer offlineQuantity;

}
