package com.akos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadWithConstructorProperties {

    private final Integer id;
    private final String name;

    public PayloadWithConstructorProperties(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PayloadWithConstructorProperties{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
