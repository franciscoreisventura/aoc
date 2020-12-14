package com.fventura.aoc2020.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fventura.aoc2020.common.Day;

public class Day10 implements Day {

    private final long[] adapters;
    private Map<Integer, Integer> multiplier = new HashMap<>();

    public Day10() {
        adapters = loadDayNumbers(10);
        Arrays.sort(adapters);
        multiplier.put(1, 1);
        multiplier.put(2, 2);
        multiplier.put(3, 4);
        multiplier.put(4, 7);
    }

    @Override
    public Object part1() {
        final long[] differences = getJoltDifferences(adapters);
        return (Arrays.stream(differences).filter(a -> a == 3).count() + 1) * Arrays.stream(differences).filter(a -> a == 1).count();
    }

    @Override
    public Object part2() {
        long[] differences = getJoltDifferences(adapters);
        long paths = 1;
        for (int i = 0; i < differences.length-1; i++) {
            if (differences[i] == 1) {
                int ones = 1;
                while (i < differences.length-1 && differences[i+1] == 1) {
                    i++;
                    ones++;
                }
                paths = paths * getMultiplier(ones);
            }
        }
        return paths;
    }

    private int getMultiplier(int index) {
        return multiplier.get(index);
    }

    final long[] getJoltDifferences(final long[] adapters) {
        long[] differences = new long[adapters.length];
        differences[0] = adapters[0];
        for (int i = 0; i < adapters.length - 1; i++) {
            differences[i + 1] = adapters[i + 1] - adapters[i];
        }
        return differences;
    }
}
