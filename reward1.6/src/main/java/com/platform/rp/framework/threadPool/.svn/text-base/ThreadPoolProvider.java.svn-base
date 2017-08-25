package com.platform.rp.framework.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 全局线程池提供者
 * 
 * 
 */
public class ThreadPoolProvider {
    private static ThreadPoolProvider threadPoolProvider = null;
    private ThreadPoolTaskExecutor poolTaskExecutor = null;

    public synchronized static ThreadPoolProvider getInstance() {
        if (threadPoolProvider == null) {
            threadPoolProvider = new ThreadPoolProvider();
        }
        return threadPoolProvider;
    }

    /**
     * 添加并执行线程任务 [不带返回值]
     * 
     * @param task
     */
    public void execute(Runnable task) {
        poolTaskExecutor.execute(task);
    }

    /**
     * 添加并执行线程任务 [带返回值]
     * 
     * @param <T>
     * 
     * @param task
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FutureTask execute(Callable task) throws Exception {
        FutureTask futureTask = new FutureTask(task);
        poolTaskExecutor.submit(futureTask);

        return futureTask;
    }
    
    /**
     * 获取线程池
     * 
     * @return
     * @throws IllegalStateException
     */
    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        return poolTaskExecutor.getThreadPoolExecutor();
    }

    /**
     * 构建全局线程池
     */
    private void buildPoolTaskExecutor() {
        poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setQueueCapacity(0);
        poolTaskExecutor.setCorePoolSize(6);
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        poolTaskExecutor.initialize();
    }

    private ThreadPoolProvider() {
        buildPoolTaskExecutor();
    }

    protected void finalize() throws Throwable {
        if (poolTaskExecutor != null)
            poolTaskExecutor.destroy();
    }

}
