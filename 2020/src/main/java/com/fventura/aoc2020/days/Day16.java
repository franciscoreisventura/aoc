package com.fventura.aoc2020.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fventura.aoc2020.common.Day;

public class Day16 implements Day {

    private final Rule[] rules;
    private final long[] myTicket;
    private final int[][] nearbyTickets;

    public Day16() {
        final String[] input = loadDayStrings(16, "\\n\\n");
        final String[] rulesString = input[0].split("\\n");
        rules = new Rule[rulesString.length];
        for (int i = 0; i < rulesString.length; i++) {
            final String[] splittedRule = rulesString[i].split(": ");
            final String ruleName = splittedRule[0];
            final String[] intervals = splittedRule[1].split(" or ");
            final String[] interval1Split = intervals[0].split("-");
            final String[] interval2Split = intervals[1].split("-");
            final Interval interval1 = new Interval(Integer.parseInt(interval1Split[0]), Integer.parseInt(interval1Split[1]));
            final Interval interval2 = new Interval(Integer.parseInt(interval2Split[0]), Integer.parseInt(interval2Split[1]));
            rules[i] = new Rule(ruleName, interval1, interval2);
        }
        myTicket = Arrays.stream(input[1].split("\\n")[1].split(",")).mapToLong(Long::parseLong).toArray();
        final String[] nearbyTicketsStrings = input[2].split("\n");
        nearbyTickets = new int[nearbyTicketsStrings.length - 1][];
        for (int i = 1; i < nearbyTicketsStrings.length; i++) {
            nearbyTickets[i - 1] = Arrays.stream(nearbyTicketsStrings[i].split(",")).mapToInt(Integer::parseInt).toArray();
        }
    }

    @Override
    public Object part1() {
        long sum = 0L;
        for (int i = 0; i < nearbyTickets.length; i++) {
            for (int j = 0; j < nearbyTickets[i].length; j++) {
                int validRules = 0;
                for (Rule r : rules) {
                    if (r.isValidValueForRule(nearbyTickets[i][j])) {
                        validRules++;
                    }
                }
                if (validRules == 0) {
                    sum = sum + nearbyTickets[i][j];
                }
            }
        }
        return sum;
    }

    @Override
    public Object part2() {
        for (int i = 0; i < nearbyTickets.length; i++) {
            for (int j = 0; j < nearbyTickets[i].length; j++) {
                int validRules = 0;
                for (Rule r : rules) {
                    if (r.isValidValueForRule(nearbyTickets[i][j])) {
                        validRules++;
                    }
                }
                if (validRules == 0) {
                    nearbyTickets[i][0] = -1;
                }
            }
        }
        for (Rule rule : rules) {
            for (int i = 0; i < nearbyTickets[0].length; i++) {
                boolean isThisOne = true;
                for (int[] ints : nearbyTickets) {
                    if (ints[0] == -1) {
                        continue;
                    }
                    if (!rule.isValidValueForRule(ints[i])) {
                        isThisOne = false;
                        break;
                    }
                }
                if (isThisOne) {
                    rule.addPossibleColumn(i);
                }
            }
        }

        boolean restart = true;
        while (restart) {
            restart = false;
            for (int i = 0; i < rules.length; i++) {
                if (rules[i].possibleValues.size() == 1) {
                    for (int j = 0; j < rules.length; j++) {
                        if (i == j) {
                            continue;
                        }
                        if (rules[j].possibleValues.size() > 1) {
                            rules[j].possibleValues.removeAll(rules[i].possibleValues);
                            restart = true;
                        }
                    }
                }
            }
        }

        long multiplication = 1;
        for (Rule rule : rules) {
            if (rule.name.contains("departure")) {
                multiplication *= myTicket[rule.possibleValues.get(0)];
            }
        }
        return multiplication;
    }

    private static class Rule {
        private final String name;
        private final Interval interval1;
        private final Interval interval2;
        private final List<Integer> possibleValues;

        public Rule(final String name, final Interval interval1, final Interval interval2) {
            this.name = name;
            this.interval1 = interval1;
            this.interval2 = interval2;
            possibleValues = new ArrayList<>();
        }

        boolean isValidValueForRule(final int value) {
            return interval1.isValidValue(value) || interval2.isValidValue(value);
        }

        void addPossibleColumn(Integer column) {
            possibleValues.add(column);
        }
    }

    private static class Interval {
        private final int min;
        private final int max;

        public Interval(final int min, final int max) {
            this.min = min;
            this.max = max;
        }

        public boolean isValidValue(final int value) {
            return value >= min && value <= max;
        }
    }
}

