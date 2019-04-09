package com.akos.futures;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public class FutureTest {

    private static class Runner {
        public String getData(){
            for (int i = 0; i<10; i++){
                showDate("inner-"+i);
                sleep();
            }
            return "done";
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void showDate(String prefix) {
        System.out.println(prefix+" "+ LocalDateTime.now().toString());
    }

    public static void main(String[] args) {
        System.out.println("Supply Async");
        supplyAsyncTest();
        System.out.println("Completed future");
        completedTest();
    }

    private static void supplyAsyncTest() {
        showDate("begin");
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> new Runner().getData());
        showDate("after create");
        sleep();
        sleep();
        showDate("before join");
        future.join();
        showDate("after join");
    }

    private static void completedTest() {
        showDate("begin");
        final CompletableFuture<String> future = CompletableFuture.completedFuture(new Runner().getData());
        showDate("after create");
        sleep();
        sleep();
        showDate("before join");
        future.join();
        showDate("after join");
    }
}
