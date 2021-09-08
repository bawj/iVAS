package com.yn.electricity.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadPoolExecutorService
 * @Author: zzs
 * @Date: 2021/1/15 10:05
 * @Description: 现场池
 */
@Component
public class ThreadPoolExecutorService {

    private final static int coreSize = Runtime.getRuntime().availableProcessors();

    private ExecutorService executorService = new ThreadPoolExecutor(coreSize, coreSize,
                                    3000, TimeUnit.SECONDS,
                                                new LinkedBlockingQueue<>(300), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 执行线程
     * @param runnable
     */
    public void executor(Runnable runnable){
        executorService.submit(runnable);
    }


}
