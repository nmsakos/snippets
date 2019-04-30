package com.akos.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadWithConstructor {

    private final Integer id;
    private final String name;

    public PayloadWithConstructor(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PayloadWithConstructor{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
