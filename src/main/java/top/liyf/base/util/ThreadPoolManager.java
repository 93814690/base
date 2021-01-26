package top.liyf.base.util;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private ThreadPoolManager() {
    }

    private static class ThreadHolder {
        private static final ExecutorService INSTANCE = new ThreadPoolExecutor(
                5, 20, 5L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ExecutorService getInstance() {
        return ThreadHolder.INSTANCE;
    }
}
