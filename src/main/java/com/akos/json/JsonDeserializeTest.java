package com.akos.json;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class JsonDeserializeTest {

    private static final File FILE = new File("target/classes/json/payload.json");

    public <T> T readPayload(Class<T> type, ParameterNamesModule... parameterNamesModule) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModules(parameterNamesModule);
            return objectMapper.readValue(FILE, type);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        JsonDeserializeTest test = new JsonDeserializeTest();

        System.out.println(test.readPayload(PayloadWithConstructorProperties.class));
        System.out.println(test.readPayload(PayloadWithConstructorCreator.class, new ParameterNamesModule()));
        System.out.println(test.readPayload(PayloadWithBuilder.class));
        System.out.println(test.readPayload(PayloadWithNonFinalConstructor.class));
    }
}
