package com.akos.enums;

import java.util.Optional;

public class EnumTests {

    private static enum MyEnumerator {
        ONE,
        TWO,
        THREE
    }

    public static void main(String[] args) {
        System.out.println(Optional.ofNullable(MyEnumerator.valueOf("FOUR")).orElse(MyEnumerator.THREE));
    }
}
