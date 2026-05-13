package xyzbank.interview.assignment.config;

import org.junit.jupiter.api.Test;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.*;

class AsyncConfigTest {

    @Test
    void shouldCreateTaskExecutorSuccessfully() {

        AsyncConfig asyncConfig =
                new AsyncConfig();

        Executor executor =
                asyncConfig.taskExecutor();

        assertNotNull(executor);

        assertTrue(
                executor instanceof ThreadPoolTaskExecutor
        );

        ThreadPoolTaskExecutor taskExecutor =
                (ThreadPoolTaskExecutor) executor;

        assertEquals(
                5,
                taskExecutor.getCorePoolSize()
        );

        assertEquals(
                10,
                taskExecutor.getMaxPoolSize()
        );

        assertEquals(
                100,
                taskExecutor.getQueueCapacity()
        );

        assertTrue(
                taskExecutor.getThreadNamePrefix()
                        .startsWith("document-upload-")
        );
    }
}

