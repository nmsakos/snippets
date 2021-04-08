package com.akos.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.time.StopWatch;

public class RunAsyncTest {

    public static void main(String[] args) {
        new RunAsyncTest().run();
    }

    private StopWatch stopWatch;

    public RunAsyncTest() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    private void run() {
        doLog(" START");
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        futures.add(CompletableFuture.runAsync(() -> doStuff("stuff1")));
        futures.add(CompletableFuture.runAsync(() -> doStuff("stuff2")));
        futures.add(CompletableFuture.runAsync(() -> doStuff("stuff3")));
        futures.add(CompletableFuture.runAsync(() -> doStuff("stuff4")));

        doLog(" Waiting for completion...");
        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
        doLog(" DONE.");
    }

    private void doStuff(String stuff) {
        doLog(" Preparing "+stuff+"...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doLog(" "+stuff+" prepared.");
    }

    private void doLog(String logMsg) {
        stopWatch.split();
        System.out.println(stopWatch.formatSplitTime()+logMsg);
    }
}
