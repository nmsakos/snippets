package com.akos.json;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializeTest {

    private static final File FILE = new File("target/classes/json/payload.json");
    private ObjectMapper objectMapper;

    public JsonDeserializeTest() {
        objectMapper = new ObjectMapper();
    }

    public <T> T readPayload(Class<T> type) {
        try {
            return objectMapper.readValue(FILE, type);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        JsonDeserializeTest test = new JsonDeserializeTest();

        final PayloadWithConstructor payloadWithConstructor = test.readPayload(PayloadWithConstructor.class);
        System.out.println(payloadWithConstructor);

        final PayloadWithBuilder payloadWithBuilder = test.readPayload(PayloadWithBuilder.class);
        System.out.println(payloadWithBuilder);

        final PayloadWithNonFinalConstructor payloadWithNonFinalConstructor = test.readPayload(PayloadWithNonFinalConstructor.class);
        System.out.println(payloadWithNonFinalConstructor);
    }
}
