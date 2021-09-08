package com.yn.electricity.sentinel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SentinelConfig
 * @Author: zzs
 * @Date: 2021/2/19 10:34
 * @Description: 获取nacos地址
 */
@Component
public class SentinelConfig {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String remoteAddress;
    @Value("${nacos.group.id}")
    private String groupId;
    @Value("${nacos.data.id}")
    private String dataId;

    public  String getRemoteAddress(){
       return remoteAddress;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getDataId() {
        return dataId;
    }
}
