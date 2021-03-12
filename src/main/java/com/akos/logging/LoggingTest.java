package com.akos.logging;

public class LoggingTest {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoggingTest.class);

    public static void main(String[] args) {
        try {
            throw new RuntimeException("This is an exception added as second object in warn.");
        } catch (Exception e) {
            LOGGER.warn("This is a warn log event. this is the first object: {}", "First object is a String", e);
        }
    }
}
