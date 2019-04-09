package com.akos.model;

import java.util.ArrayList;
import java.util.List;

public class Payload {
    private Integer id;
    private String name;
    private List<String> children;

    private Payload() {

    }

    private Payload(Builder builder) {
        id = builder.id;
        name = builder.name;
        children = builder.children;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    public static PayloadBuilder builder(){
        return new PayloadBuilder();
    }

    public static final class PayloadBuilder {

        private Integer id;
        private String name;
        private List<String> children = new ArrayList<>();

        private PayloadBuilder() {
        }

        public static PayloadBuilder aPayload() {
            return new PayloadBuilder();
        }

        public PayloadBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PayloadBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PayloadBuilder addChild(String child) {
            this.children.add(child);
            return this;
        }

        public Payload build() {
            Payload payload = new Payload();
            payload.children = this.children;
            payload.id = this.id;
            payload.name = this.name;
            return payload;
        }
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private List<String> children;

        public Builder() {
        }

        public Builder(Payload copy) {
            this.id = copy.getId();
            this.name = copy.getName();
            this.children = copy.getChildren();
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withChildren(List<String> children) {
            this.children = children;
            return this;
        }

        public Payload build() {
            return new Payload(this);
        }
    }
}
