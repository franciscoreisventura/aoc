package com.fventura.aoc2020.days;

import com.fventura.aoc2020.common.Day;

public class Day18 implements Day {

    final String[] input;

    public Day18() {
        input = loadDayStrings(18);
    }

    @Override
    public Object part1() {
        long sum = 0;
        for (String expression : input) {
            sum += solveExpression(expression);
        }
        return sum;
    }

    private long solveExpression(String expression) {
        long result;
        if (expression.contains("(")) {
            int startIndex = expression.indexOf('(');
            int endIndex = findEnd(expression, startIndex);
            long innerResult = solveExpression(expression.substring(startIndex + 1, endIndex));
            expression = expression.substring(0, startIndex) + innerResult + expression.substring(endIndex + 1);
            return solveExpression(expression);
        }

        String[] splitted = expression.split(" ");

        result = Long.parseLong(splitted[0]);
        char operation;
        long secondOperand;
        int i = 1;
        while (i < splitted.length) {
            operation = splitted[i++].charAt(0);
            secondOperand = Long.parseLong(splitted[i++]);
            if (operation == '+') {
                result += secondOperand;
            } else {
                result *= secondOperand;
            }
        }

        return result;
    }

    private int findEnd(String expression, int startIndex) {
        int countOpen = 0;
        for (int i = startIndex + 1; i < expression.length(); i++) {
            if (expression.charAt(i) == ')') {
                if (countOpen > 0) {
                    countOpen--;
                    continue;
                }
                return i;
            }
            if (expression.charAt(i) == '(') {
                countOpen++;
            }
        }
        return -1;
    }

    @Override
    public Object part2() {
        long sum = 0;
        for (String expression : input) {
            sum += solveExpression2(expression);
        }
        return sum;
    }

    private long solveExpression2(String expression) {
        long result = 0;
        if (expression.contains("(")) {
            int startIndex = expression.indexOf('(');
            int endIndex = findEnd(expression, startIndex);
            long innerResult = solveExpression2(expression.substring(startIndex + 1, endIndex));
            expression = expression.substring(0, startIndex) + innerResult + expression.substring(endIndex + 1);
            return solveExpression2(expression);
        }

        if (expression.contains("*")) {
            String[] splittedMul = expression.split(" \\* ");
            long multi = 1;
            for (String splitted : splittedMul) {
                multi *= solveExpression2(splitted);
            }
            return multi;
        }

        String[] splitted = expression.split(" ");

        result = Long.parseLong(splitted[0]);
        char operation;
        long secondOperand;
        int i = 1;
        while (i < splitted.length) {
            operation = splitted[i++].charAt(0);
            secondOperand = Long.parseLong(splitted[i++]);
            if (operation == '+') {
                result += secondOperand;
            } else {
                result *= secondOperand;
            }
        }

        return result;
    }
}

