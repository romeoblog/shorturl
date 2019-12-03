package cn.willlu.shorturl.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
@Slf4j
@Service
public class AsyncExecuteService {

    private static ExecutorService executor = null;
    private static final int CORE_POOL_SIZE = 32;
    private static final int MAXI_MUM_POOL_SIZE = 96;

    @PostConstruct
    private void init() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXI_MUM_POOL_SIZE, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
        log.info("==== Thread executor init success! ====");
    }

    @PreDestroy
    private void destroy() {
        if (executor != null) {
            executor.shutdownNow();
            log.info("==== Thread executor shutdownNow success! ====");
        }
    }

    public static void execute(Runnable command) {
        executor.submit(command);
    }
}
