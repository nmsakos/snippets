package com.akos.optionals;

import java.util.Optional;

public class OptionalMethodChainTest {

    public static void main(String[] args) {
        Throwable exception =
                new RuntimeException("top Level with two below",
                        new RuntimeException("second level with one below",
                                new RuntimeException("third level")));
        System.out.println(getTop(exception));

        exception =
                new RuntimeException("top Level with one below",
                        new RuntimeException("second level with none below"));
        System.out.println(getTop(exception));

        exception =
                new RuntimeException("top Level with none below");

        System.out.println(getTop(exception));
    }

    private static String getTop(Throwable exception) {
        try {
            throw Optional.ofNullable(exception.getCause())
                    .map(c -> Optional.ofNullable(c.getCause()).orElse(c))
                    .orElse(exception);
        } catch (Throwable e) {
            return e.getMessage();
        }
    }
}
