package com.akos;

public class MultiLevelCatchTest {

    private static class FirstLevelException extends Exception {}
    private static class SecondLevelException extends RuntimeException {}

    public static void main(String[] args) {
        System.out.println("zipkin startspan 1");
        //method2();
        try {
            System.out.println("zipkin startspan 2");
            try {
                //method2();
                //method1();
//            System.out.println("zipkin stopspan (try)");
                return;
//        } catch (FirstLevelException e) {
//            System.out.println("FirstLevelException catched");
//            System.out.println("zipkin stopspan (catch)");
            } finally {
                System.out.println("zipkin stopspan (finally2)");
            }
        } finally {
            System.out.println("zipkin stopspan (finally1)");
        }
    }

    static void method1() throws FirstLevelException {
        throw new SecondLevelException();
    }

    static void method2() {
        throw new SecondLevelException();
    }
}
