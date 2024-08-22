package com.dakshay.userfeed.repo;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "affection-processor")
    public Executor affectionProcessorExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Core threads
        executor.setMaxPoolSize(50);  // Maximum threads
        executor.setQueueCapacity(200); // Queue capacity
        executor.setThreadNamePrefix("Thread-affection-processor-");
        executor.initialize();
        return executor;
    }

}