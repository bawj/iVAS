package com.yn.electricity.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FeignConfiguration
 * @Author: zzs
 * @Date: 2021/2/2 11:15
 * @Description:
 */
@Configuration
public class FeignConfigurations {
    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}
