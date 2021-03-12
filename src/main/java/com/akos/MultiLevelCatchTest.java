package com.akos;

public class MultiLevelCatchTest {

    private static class FirstLevelException extends Exception {}
    private static class SecondLevelException extends RuntimeException {}

    public static void main(String[] args) {
        System.out.println("zipkin startspan");
        //method2();
        try {
            //method2();
            method1();
            System.out.println("zipkin stopspan (try)");
        } catch (FirstLevelException e) {
            System.out.println("FirstLevelException catched");
            System.out.println("zipkin stopspan (catch)");
        } finally {
            System.out.println("zipkin stopspan (finally)");
        }
    }

    static void method1() throws FirstLevelException {
        throw new SecondLevelException();
    }

    static void method2() {
        throw new SecondLevelException();
    }
}
