package com.akos.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = PayloadWithBuilder.Builder.class)
public class PayloadWithBuilder {

    private final Integer id;
    private final String name;

    private PayloadWithBuilder(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    @Override
    public String toString() {
        return "PayloadWithBuilder{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    public static final class Builder {
        private Integer id;
        private String name;

        public Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public PayloadWithBuilder build() {
            return new PayloadWithBuilder(this);
        }
    }
}
