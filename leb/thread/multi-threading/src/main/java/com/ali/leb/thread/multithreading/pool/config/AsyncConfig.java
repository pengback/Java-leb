package com.ali.leb.thread.multithreading.pool.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: aliber
 * @Date: 2021/4/12 下午10:01
 **/

@Configuration
@EnableAsync
@EnableScheduling
@Slf4j
public class AsyncConfig implements SchedulingConfigurer {

    protected final int SCHEDULE_POOL_SIZE = 5;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(schedulerThreadPool());
    }

    /**
     * 第一种线程池定义方式，可替代CachedThreadPool, FixedThreadPool, SingleThreadPoolExecutor这三种
     * Spring线程池
     *
     * @return
     */
    @Lazy
    @Bean(name = "threadPoolTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        // 封装的是原生的ThreadPoolTaskExecutor类型线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数（从获取硬件）：线程池创建的时候初始化线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        log.info("init threadPoolTaskExecutor corePoolSize is :" + corePoolSize);
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(corePoolSize * 2);
        executor.setQueueCapacity(500);
        // 设置允许线程的空闲时间 60秒，当超过了核心线程数之外的线程在空闲时间到达之后会被销户
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("My-Async-");
        executor.initialize();
        return executor;
    }


    /**
     * 第二种线程池定义方式，使用的是 WorkStealingPool
     * java8 抢占式线程池
     *
     * @return
     */
    @Lazy
    @Bean(name = "workStealingPool", destroyMethod = "shutdown")
    public ExecutorService workStealingPool() {
        ExecutorService executorService = Executors.newWorkStealingPool();
        return executorService;
    }


    /**
     * 第三种线程池定义方式， 周期任务线程池
     * 周期任务线程池
     *
     * @return
     */
    @Lazy
    @Bean(name = "schedulerThreadPool", destroyMethod = "shutdown")
    public ExecutorService schedulerThreadPool() {
        return Executors.newScheduledThreadPool(SCHEDULE_POOL_SIZE);
    }

}



