package com.akos.taxicabnumbers;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Stream;

/*
    This is an unattended coding interview, please start solving
    the task as soon as you get here.
    You have 90 minutes to solve the following task.

    Implement solveCubicEquation() method given below
    such that for all combinations of 4 integers
    (a, b, c, d), each ranging from 1 to 32,
    it prints to console those that solve this equation:
        a^3 + b^3 = c^3 + d^3

    Return all printed solutions as a list of strings.

    Print solutions in this format:
        9^3 + 10^3 = 1^3 + 12^3 = 1729

    Do not print trivial solutions like:
        (9, 10, 10, 9), (9, 9, 9, 9), etc.

    Print each solution only once, for example,
    if solution (9, 10, 1, 12) has been printed
    do not print its permutations like:
        (9, 10, 12, 1), (10, 9, 1, 12),
        (1, 12, 9, 10), etc.

    Your code should print these solutions (in any order):

        9^3 + 10^3 = 1^3 + 12^3 = 1729
        9^3 + 15^3 = 2^3 + 16^3 = 4104
        18^3 + 20^3 = 2^3 + 24^3 = 13832
        18^3 + 30^3 = 4^3 + 32^3 = 32832
        19^3 + 24^3 = 10^3 + 27^3 = 20683

    Tip: start with a brute force solution.

    --
    0. Have you printed your solutions to console?

    1. What is time complexity of your algorithm?
       Write your answer here -> O(n^2)

    2. Can you make it faster?
        Time complexity could not be decreased, only inside the method could be some place for improvements, but those would not be significat. Have tried some and on this tool it was not measureable.

    3. What is space complexity of your algorithm?
       Write your answer here -> O(1)

    4. Can you optimize memory usage?
        Experimenting with the change of the Map to an array would be a way, but I would not expect huge difference. We would lose more on code complexity.
        An other way would be to use Set<Set<Integer>> instead of the Set<String> in the map.
*/

class MyTaxicabNumbers {

    private static List<String> solveCubicEquation() {
        // write your code here

        Map<Integer, Set<String>> pairs = new HashMap<>();
        for (int i = 1; i < 32; i++) {
            for (int j = 2; j <= 32; j++) {
                int i3 = (int) Math.pow(i, 3);
                int j3 = (int) Math.pow(j, 3);

                int sum = i3 + j3;

                int a = i, b = j;
                if (j < i) {
                    a = j;
                    b = i;
                }

                pairs.computeIfAbsent(sum, key -> new HashSet<>())
                        .add(a + "^3 + " + b + "^3");

            }

        }

        return pairs.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(entry -> mergeEquations(entry.getValue()) + entry.getKey())
                .peek(System.out::println)
                .collect(toList());

    }

    private static String mergeEquations(Set<String> equations) {
        StringJoiner joiner = new StringJoiner(" = ", "", " = ");
        equations.forEach(joiner::add);
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
