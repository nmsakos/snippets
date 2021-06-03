package com.akos.threadpool;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import com.google.common.util.concurrent.*;

public class ThreadPoolParallelStreamTest {

    public static final int END_INCLUSIVE = 1000;

    public static void main(String[] args) throws Exception {
        new ThreadPoolParallelStreamTest().run();
    }

    private void run() throws ExecutionException, InterruptedException {
        ExecutorService executor = createThreadPool();
        long start = currentTime();
        System.out.println("Stating in main");
        List<Long> futures = runStream();
        System.out.printf("Running in main thread took: %d \r\n", currentTime()-start );

        start = currentTime();
        System.out.println("Stating in executor");
        List<CompletableFuture<Long>> futures2 = executor.submit(() ->
                getFutures(executor))
                .get();

        CompletableFuture.allOf(futures2.toArray(CompletableFuture[]::new))
                .thenApplyAsync(v -> futures2.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();
        System.out.printf("Running in executor thread took: %d \r\n", currentTime()-start );

        start = currentTime();
        System.out.println("Stating in executor w/o future");
        List<Long> longs = executor.submit(this::runStream)
                .get();
        System.out.printf("Running in executor w/o future thread took: %d \r\n", currentTime()-start );

    }

    private long currentTime() {
        return new Date().getTime();
    }

    private List<CompletableFuture<Long>> getFutures(ExecutorService executor) {
        return LongStream.rangeClosed(1, END_INCLUSIVE).parallel().boxed()
                .map(l -> CompletableFuture.supplyAsync(() -> doSomething(l), executor))
                .collect(Collectors.toList());
    }

    private List<Long> runStream() {
        return LongStream.rangeClosed(1, END_INCLUSIVE).parallel().boxed()
                .map(this::doSomething)
                .collect(Collectors.toList());
    }

    private ExecutorService createThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("test-thread-%d")
                .setDaemon(true)
                .build();

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(5000);
        return new ThreadPoolExecutor(50,
                50,
                0L, TimeUnit.MILLISECONDS,
                workQueue,
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private long doSomething(long l) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("[%d]Thread name: %s \r\n", l, Thread.currentThread().getName());
        return l;
    }
}
