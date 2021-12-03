package com.fventura.aoc2020.days;

import com.fventura.aoc2020.common.Day;

public class Day6 implements Day {

    final String[] groups;

    public Day6() {
        groups = loadDayStrings(6, "\\n\\s");
    }

    @Override
    public Object part1() {
        int sum = 0;
        for (String group : groups) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (countChar(group, c) >= 1) {
                    sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public Object part2() {
        int sum = 0;
        for (String group : groups) {
            for (char c = 'a'; c <= 'z'; c++) {
                final long groupSize = countChar(group, '\n') + 1;
                if (countChar(group, c) == groupSize) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private long countChar(final String group, final char c) {
        return group.chars().filter(ch -> ch == c).count();
    }
}