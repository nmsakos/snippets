package com.akos.client;

import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloClientTest {

    @Test
    void getData() throws ExecutionException, InterruptedException {
        var helloClient = new HelloClient();
        var data = helloClient.getData(0);
        assertNotEquals("No payload!", data);
        data = helloClient.getData(1);
        assertEquals("No payload!", data);}
}