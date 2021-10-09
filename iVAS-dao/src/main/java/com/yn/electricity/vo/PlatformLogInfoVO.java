package com.yn.electricity.vo;

import lombok.Data;

/**
 * @author admin
 * Date 2021/10/9 14:39
 * Description
 **/
@Data
public class PlatformLogInfoVO {

    private String name;

    private String ip;

    private Integer port;

    private Integer online;

    private String createTime;

}
