package com.akos.brackets;


import java.util.List;
import java.util.Stack;

public class EvaluateBrackets {
    public static void main(String[] args) {
        final EvaluateBrackets evaluateBrackets = new EvaluateBrackets();
        List<String> input = List.of("()",
                "[)",
                "[()]",
                "[[((()))]]",
                "[()()]",
                ")(",
                "[[]");
        input.forEach(expression ->
                System.out.println(expression + " == " + evaluateBrackets.eval(expression)));
    }


    private boolean eval(String expression) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            final char current = expression.charAt(i);
            if (isOpening(current)) {
                stack.push((int) current);
                continue;
            }
            if (isClosing(current)) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!arePairs(stack.pop(), current)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean arePairs(int charA, char charB) {
        return (charA == '(' && charB == ')')
                || (charA == ')' && charB == '(')
                || (charA == '[' && charB == ']')
                || (charA == ']' && charB == '[');
    }

    private boolean isOpening(char c) {
        return c == '(' || c == '[';
    }

    private boolean isClosing(char c) {
        return c == ')' || c == ']';
    }


}
