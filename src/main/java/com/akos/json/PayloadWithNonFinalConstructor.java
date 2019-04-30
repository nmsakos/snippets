package com.akos.json;

public class PayloadWithNonFinalConstructor {

    private Integer id;
    private String name;

    public PayloadWithNonFinalConstructor() {
    }

    public PayloadWithNonFinalConstructor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PayloadWithNonFinalConstructor{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
