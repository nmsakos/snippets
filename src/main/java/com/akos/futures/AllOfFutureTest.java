package com.akos.futures;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AllOfFutureTest {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Future1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Future2");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Exception thrown");
        });
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> "Future4");

        List<CompletableFuture<String>> futures = List.of(future1, future2, future3, future4);
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .exceptionally(throwable1 -> handleException(throwable1, "allOf"))
                .thenApply(v -> futures.stream()
                        .filter(stringCompletableFuture -> !stringCompletableFuture.isCompletedExceptionally())
                        .map(CompletableFuture::join)
                        .collect(toList()))
                .exceptionally(throwable1 -> handleException(throwable1, "thanApply"))
                .join()
                .forEach(System.out::println);

    }

    private static <T> T handleException(Throwable throwable, String stage) {
        System.out.println(stage + " has failed. " + throwable.getLocalizedMessage());
        return null;
    }
}
