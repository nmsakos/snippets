package com.akos.json;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PayloadWithConstructorCreator {

    private final Integer id;
    private final String name;

    @JsonCreator
    public PayloadWithConstructorCreator(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PayloadWithConstructorCreator{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
