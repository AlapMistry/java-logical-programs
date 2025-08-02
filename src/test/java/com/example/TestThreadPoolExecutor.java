package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.Test;

public class TestThreadPoolExecutor {

    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    public void Test_execute_noPolicy() {
        threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2));
        threadPoolExecutor.execute(() -> waitFor(1000)); // 1

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        threadPoolExecutor.execute(() -> queue.offer("First")); // 2
        threadPoolExecutor.execute(() -> queue.offer("Second")); // 3
        assertThrows(RejectedExecutionException.class, () -> threadPoolExecutor.execute(() -> queue.offer("Third"))); // 4

        waitFor(1500);
        threadPoolExecutor.shutdown();
    }

    @Test
    public void Test_execute_withGrowPolicy() {
        threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2),
                new GrowPolicy());
        threadPoolExecutor.execute(() -> waitFor(100)); // 1

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        threadPoolExecutor.execute(() -> queue.offer("First")); // 2
        threadPoolExecutor.execute(() -> queue.offer("Second")); // 3
        threadPoolExecutor.execute(() -> queue.offer("Third")); // 4
        // threadPoolExecutor.execute(() -> queue.offer("Four"));
        // threadPoolExecutor.execute(() -> queue.offer("Five"));

        waitFor(150);

        List<String> results = new ArrayList<>();
        queue.drainTo(results);
        assertTrue(results.containsAll(Arrays.asList("First", "Second", "Third")));
        assertEquals(2, threadPoolExecutor.getMaximumPoolSize());

        threadPoolExecutor.shutdown();
    }

    private static class GrowPolicy implements RejectedExecutionHandler {

        private final Lock lock = new ReentrantLock();

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            lock.lock();

            try {
                System.out.println("Before max pool size: " + executor.getMaximumPoolSize());
                executor.setMaximumPoolSize(executor.getMaximumPoolSize() + 1);
                System.out.println("After max pool size: " + executor.getMaximumPoolSize());
            } finally {
                lock.unlock();
            }

            executor.submit(r);
        }
    }

    private void waitFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
