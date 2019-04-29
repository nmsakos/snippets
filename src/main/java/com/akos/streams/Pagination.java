package com.akos.streams;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pagination {

    private static class Page {
        final String head;
        final List<Long> ids;

        private Page(final Builder builder) {
            head = builder.head;
            ids = builder.ids;
        }

        @Override
        public String toString() {
            return "Page{"
                    + "head='" + head + '\''
                    + ", ids=" + ids
                    + '}';
        }

        public static final class Builder {
            private String head;
            private List<Long> ids;

            public Builder() {
            }

            public Builder withHead(String head) {
                this.head = head;
                return this;
            }

            public Builder withIds(List<Long> ids) {
                this.ids = ids;
                return this;
            }

            public Page build() {
                return new Page(this);
            }
        }
    }

    public static void main(String[] args) {
        List<Long> list = List.of(1121L, 2123L, 3567L, 42345L, 589L, 617654L, 756333L);
        int pageSize = 3;
        final List<Page> result = paginate(list, pageSize);

        System.out.println(result);
    }

    private static List<Page> paginate(List<Long> list, int pageSize) {
        return IntStream.range(0, (list.size() + pageSize - 1) / pageSize)
                .boxed()
                .map(i -> list.subList(i * pageSize, min(pageSize * (i + 1), list.size())))
                .map(longs -> new Page.Builder().withHead("something").withIds(longs).build())
                .collect(Collectors.toList());
    }
}
