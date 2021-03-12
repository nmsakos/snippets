package com.akos.taxicabnumbers;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaxicabNumbers {
    private static int MIN = 1;
    private static int MAX = 32;

    private static List<String> solveCubicEquation() {
        Map<Integer, List<String>> pairs = new HashMap<>();

        for (int i = MIN; i < MAX; i++) {
            for (int j = MIN + 1; j <= MAX; j++) {
                if (i == j) {
                    continue;
                }
                int sum = (int) Math.pow(i, 3) + (int) Math.pow(j, 3) ;
                int a = i, b = j;
                if (j > i) {
                    a = j;
                    b = i;
                }
                final String str = String.format("/d^3 + /d^3",a, b);
                List<String> current = pairs.computeIfAbsent(sum, k -> new ArrayList<>());
                if (!current.contains(str)) {
                    current.add(str);
                }
            }
        }

        return pairs.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(entry -> joinEquation(entry.getValue()) + entry.getKey())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    private static String joinEquation(List<String> value) {
        StringJoiner joiner = new StringJoiner(" = ", "", " = ");
        value.forEach(joiner::add);
        return joiner.toString();
    }

    private static List<String> expectedSolutions = asList(
            "9^3 + 10^3 = 1^3 + 12^3 = 1729",
            "9^3 + 15^3 = 2^3 + 16^3 = 4104",
            "18^3 + 20^3 = 2^3 + 24^3 = 13832",
            "18^3 + 30^3 = 4^3 + 32^3 = 32832",
            "19^3 + 24^3 = 10^3 + 27^3 = 20683"
    );

    private static Map<Integer, Set<Set<Integer>>> mapOf(List<String> solutions) {
        return solutions.stream().map(String::trim).map(s -> s.split("\\s=\\s"))
                .collect(toMap(parts -> valueOf(parts[2].replace(",", "")),
                        parts -> Stream.of(parts[0], parts[1])
                                .map(s -> s.split("\\s\\+\\s"))
                                .map(pairs -> Stream.of(pairs)
                                        .map(s -> s.endsWith("^3") ? s : "0")
                                        .map(s -> s.replace("^3",""))
                                        .map(s -> Integer.valueOf(s)).collect(toSet()))
                                .collect(toSet())));
    }

    private static void assertSolutionIsCorrect(List<String> actualResult) {
        String verdict = mapOf(actualResult).equals(mapOf(expectedSolutions)) ?
                "Passed" : "Almost there, keep trying";
        System.out.println(verdict);
    }

    public static void main(String[] args) {
        assertSolutionIsCorrect(solveCubicEquation());
    }
}


