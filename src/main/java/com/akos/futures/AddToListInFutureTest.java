package com.akos.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.StopWatch;

public class AddToListInFutureTest {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        new AddToListInFutureTest().runAddInFuture();
        stopWatch.split();
        System.out.println(stopWatch.formatSplitTime());
        new AddToListInFutureTest().runAddInAllOf();

    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void runAddInFuture() {
        List<Integer> theList = new ArrayList<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            final Integer value = i;
            futures.add(CompletableFuture.runAsync(() -> {
                sleep();
                theList.add(value);
            }));
        }

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join))
            .join();

        System.out.println(theList.size());
    }

    private void runAddInAllOf() {
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            final int value = i;
            futures.add(CompletableFuture.supplyAsync(() -> {
                sleep();
                return value;
            }));
        }

        List<Integer> theList = new ArrayList<>(CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()))
            .join());

        System.out.println(theList.size());
    }
}
