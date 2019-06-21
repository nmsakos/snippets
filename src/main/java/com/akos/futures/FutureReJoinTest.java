package com.akos.futures;

import java.util.concurrent.CompletableFuture;

public class FutureReJoinTest {

    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage1";});
        System.out.println(future.join());
        future = future.thenApply(s -> s+" stage2");
        System.out.println(future.join());
    }
}
