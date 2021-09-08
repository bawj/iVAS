package com.yn.electricity.rpc;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ExceptionUtil
 * @Author: zzs
 * @Date: 2021/2/2 10:59
 * @Description:
 */
public class ExceptionUtil {

    public static void handleException(BlockException ex) {
        System.out.println("rpc调用异常，稍后重试");
    }
}
