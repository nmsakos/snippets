package com.akos.uribuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class QueryParamExtractor {

    private class Pair {
        int web = 0;
        int app = 0;

        public Pair(int web, int app) {
            this.web = web;
            this.app = app;
        }

        @Override
        public String toString() {
            return "Pair{"
                    + "web=" + web
                    + ", app=" + app
                    + '}';
        }
    }

    public static void main(String[] args) {
        QueryParamExtractor extractor = new QueryParamExtractor();
        extractor.extract();
    }

    private void extract() {
        Map<String, Pair> aggr = new HashMap<>();
        try {
            final List<String> lines = FileUtils.readLines(new File("C:\\Users\\akos_nemes\\Downloads\\chun_url_listings.csv"), Charset.defaultCharset());
            lines.remove(0);
            for (int i = 0; i < lines.size(); i++) {
                final Map<String, List<String>> map = processLine(lines.get(i));
                final String channel = map.keySet().iterator().next();
                final List<String> params = map.get(channel);
                params.forEach(p -> {
                    if (aggr.containsKey(p)) {
                        if ("APP".equals(channel)) {
                            aggr.get(p).app = ++aggr.get(p).app;
                        } else {
                            aggr.get(p).web = ++aggr.get(p).web;
                        }
                    } else {
                        aggr.put(p, "APP".equals(channel) ? new Pair(0, 1) : new Pair(1, 0));
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("param,web,app");
        aggr.entrySet().forEach(this::printEntry);
    }

    private void printEntry(Map.Entry<String, Pair> entry) {
        StringBuilder sb = new StringBuilder();
        final String s = sb.append("\"")
                .append(entry.getKey())
                .append("\"")
                .append(",")
                .append(entry.getValue().web)
                .append(",")
                .append(entry.getValue().app)
                .toString();
        System.out.println(s);
    }

    private Map<String, List<String>> processLine(String input) {
        String[] rowArray = input.split(",");
        Optional<String> channel = atArray(rowArray, 0);

        Optional<String> url = atArray(rowArray, 1);
        final List<String> params = processUrl(url.orElse(""));
        return Map.of(channel.orElse(""), params);
    }

    private List<String> processUrl(String url) {
        Optional<String> queryParamString = atArray(url.split("\\?"), 1);
        return processParams(queryParamString.orElse(""));
    }

    private List<String> processParams(String qParams) {
        List<String> params = List.of(qParams.split("\\&"));
        return params.stream()
                .map(p -> p.split("="))
                .map(a -> atArray(a, 0).orElse(""))
                .filter(s -> !"".equals(s))
                .collect(Collectors.toList());
    }

    private Optional<String> atArray(String[] array, int pos) {
        if (array.length > pos) {
            return Optional.of(array[pos]);
        } else {
            return Optional.empty();
        }
    }
}
