package cn.willlu.shorturl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
@Slf4j
@Service
public class AsyncTaskService {

    private static int MAX_POOL_SIZE;

    private static int DELAY;

    private static final ScheduledExecutorService workerPool = Executors.newScheduledThreadPool(MAX_POOL_SIZE, new WorkThreadFactory());

    @Value("${thread.pool.corePoolSize}")
    public void setCorePoolSize(int corePoolSize) {
        MAX_POOL_SIZE = corePoolSize;
    }

    @Value("${thread.pool.delay}")
    public void setDelay(int delay) {
        DELAY = delay;
    }

    @PostConstruct
    private void init() {
        log.info("Class: AsyncTaskService init...");
    }

    @PreDestroy
    private void destroy() {
        log.info("Class: AsyncTaskService destroying...");
        workerPool.shutdownNow();
    }

    public static Future<?> asyncSendTask(Callable<?> callable) {
        return workerPool.schedule(callable, DELAY, TimeUnit.SECONDS);
    }

    private static class WorkThreadFactory implements ThreadFactory {
        private final AtomicInteger num = new AtomicInteger(0);
        private final String THREAD_NAME_PREFIX = "task-worker-";

        @Override
        public Thread newThread(Runnable r) {
            String threadName = THREAD_NAME_PREFIX + num.getAndIncrement();
            Thread thread = new Thread(r, threadName);
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;
        }
    }
}
