package com.yn.electricity.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.*;

/**
 * @author Administrator
 * @date 2020/5/20 0020 09:56 
 * ThreadPoolExecutor
 **/
public class ThreadPoolExecutorUtil {


    public static int CORE_POOL_SIZE = 1;
    public static int MAXIMUM_POOL_SIZE = 1;
    public static int KEEP_ALIVE_TIME = 30;

    /**
     * @param corePoolSize 指定了线程池中的现场数量,它的数量决定了添加任务是开辟新的线程去运行，还是放到workQueue任务队列中取
     * @param maximumPoolSize 指定了线程池中最大的线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量
     * @param keepAliveTime 当线程池中的空闲线程数量超过corePoolSize时，多余的线程会在多长时间类被摧毁
     * @param unit keepAliveTime 的单位  默认TimeUnit.MILLISECONDS
     * @return ExecutorService
     */
    public static ExecutorService getExecutorService(int corePoolSize , int maximumPoolSize , int keepAliveTime , TimeUnit unit){
        //BlockingQueue<Runnable>(3) workQueue 任务队列，被添加到线程池中，但尚未被执行的任务；
        // 它一般分为直接提交队列、有界任务队列、无界任务队列、有限任务队列几种 3 代表等候区
        // 当 corePoolSize 正在运行时 后添加的任务会等待 3 代表等待上限
        //默认大小 Integer.MAX_VALUE

        //ThreadFactory threadFactory 线程工厂，用于创建线程，一般默认即可
        //RejectedExecutionHandler handler 拒绝策略；当任务太多来不及处理时，如何拒绝任务 默认new AbortPolicy()
        //拒绝策略有四个参数: ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
                            //ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
                            //ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面(等待时间最久的)的任务，然后重新尝试执行任务（重复此过程）
                            //ThreadPoolExecutor.CallerRunsPolicy：多出的任务 由调用线程处理该任务

        if (unit == null){
            unit = TimeUnit.MILLISECONDS;
        }
        //使用ThreadFactoryBuilder 必须使用 com.google.guava 的 guava 的包
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("\"thread-%d\"").build();


        return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, unit,new LinkedBlockingQueue<Runnable>(20),threadFactory);
    }

    public static void setCorePoolSize(int corePoolSize) {
        CORE_POOL_SIZE = corePoolSize;
    }

    public static void setMaximumPoolSize(int maximumPoolSize) {
        MAXIMUM_POOL_SIZE = maximumPoolSize;
    }

    public static void setKeepAliveTime(int keepAliveTime) {
        KEEP_ALIVE_TIME = keepAliveTime;
    }
}
