package com.akos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLoggingTest {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionLoggingTest.class);

    public static void main(String[] args) {
        try {
            throw new Exception("This is a test exception.");
        } catch (Exception e) {
            LOG.error("This is a LOG.error in the exception branch", e);
        }
    }
}
