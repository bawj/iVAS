package com.yn.electricity.vo;

import lombok.Data;

/**
 * @author admin
 * Date 2021/10/9 14:41
 * Description
 **/
@Data
public class DeviceLogInfoVO {

    private String name;

    private String ip;

    private Integer port;

    private Integer online;

    private String createTime;

}
