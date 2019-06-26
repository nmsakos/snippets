package com.akos.streams;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class BuilderCollectorTest {

    public static void main(String[] args) {
        TheClass first = new TheClass.Builder().withFirstField("first").build();
        TheClass second = new TheClass.Builder().withSecondField("second").build();
        TheClass third = new TheClass.Builder().withThirdField("third").build();
        final TheClass result = List.of(first, second, third)
                .stream()
                .collect(new BuilderCollector());

        System.out.println(result);
    }

    private static class BuilderCollector
        implements Collector<TheClass, TheClass.Builder, TheClass> {

        @Override
        public Supplier<TheClass.Builder> supplier() {
            return TheClass.Builder::new;
        }

        @Override
        public BiConsumer<TheClass.Builder, TheClass> accumulator() {
            return (builder, theClass) -> {
                if (theClass.getFirstField() != null) {
                    builder.withFirstField(theClass.getFirstField());
                }
                if (theClass.getSecondField() != null) {
                    builder.withSecondField(theClass.getSecondField());
                }
                if (theClass.getThirdField() != null) {
                    builder.withThirdField(theClass.getThirdField());
                }
            };
        }

        @Override
        public BinaryOperator<TheClass.Builder> combiner() {
            return (builder, builder2) -> {
                accumulator().accept(builder2, builder.build());
                return builder2;
            };
        }

        @Override
        public Function<TheClass.Builder, TheClass> finisher() {
            return TheClass.Builder::build;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.UNORDERED);
        }
    }

    private static class TheClass {
        private final String firstField;
        private final String secondField;
        private final String thirdField;

        private TheClass(Builder builder) {
            firstField = builder.firstField;
            secondField = builder.secondField;
            thirdField = builder.thirdField;
        }

        public String getFirstField() {
            return firstField;
        }

        public String getSecondField() {
            return secondField;
        }

        public String getThirdField() {
            return thirdField;
        }

        @Override
        public String toString() {
            return "TheClass{"
                    + "firstField='" + firstField + '\''
                    + ", secondField='" + secondField + '\''
                    + ", thirdField='" + thirdField + '\''
                    + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TheClass theClass = (TheClass) o;
            return Objects.equals(firstField, theClass.firstField)
                    && Objects.equals(secondField, theClass.secondField)
                    && Objects.equals(thirdField, theClass.thirdField);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstField, secondField, thirdField);
        }

        public static final class Builder {
            private String firstField;
            private String secondField;
            private String thirdField;

            public Builder() {
            }

            public Builder withFirstField(String firstField) {
                this.firstField = firstField;
                return this;
            }

            public Builder withSecondField(String secondField) {
                this.secondField = secondField;
                return this;
            }

            public Builder withThirdField(String thirdField) {
                this.thirdField = thirdField;
                return this;
            }

            public TheClass build() {
                return new TheClass(this);
            }
        }
    }
}
