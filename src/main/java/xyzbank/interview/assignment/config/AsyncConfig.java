 package xyzbank.interview.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration

@EnableAsync

public class AsyncConfig {

    /**
     * Thread pool configuration for asynchronous tasks.
     *
     * Used for:
     * - Background document upload
     * - Non-blocking processing
     */

    @Bean(name = "taskExecutor")

    public Executor taskExecutor() {

        ThreadPoolTaskExecutor executor =
                new ThreadPoolTaskExecutor();

        // Minimum number of threads

        executor.setCorePoolSize(5);

        // Maximum allowed threads

        executor.setMaxPoolSize(10);

        // Queue size before creating new threads

        executor.setQueueCapacity(100);

        // Thread naming pattern

        executor.setThreadNamePrefix(
                "document-upload-"
        );

        executor.initialize();

        return executor;
    }
}
 