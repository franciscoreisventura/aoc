package com.fventura.aoc2020.days;

import com.fventura.aoc2020.common.Day;

public class Day15 implements Day {

    private final long[] input;

    public Day15() {
        input = loadDayNumbers(15, ",");
    }

    @Override
    public Object part1() {
        return run(2020);
    }

    @Override
    public Object part2() {
        return run(30000000);
    }

    public int run(final int iterations) {
        int[] lastTime = new int[iterations];
        int[] lastLastTime = new int[iterations];
        int lastNumber = (int) input[0];
        lastTime[(int) input[0]] =  1;
        lastLastTime[(int) input[0]] = 1;
        for (int i = 1; i < iterations; i++) {
            if (i < input.length) {
                lastNumber = (int) input[i];
                lastLastTime[lastNumber] = lastTime[lastNumber] = i + 1;
                continue;
            }
            lastNumber = lastTime[lastNumber] - lastLastTime[lastNumber];
            if (lastLastTime[lastNumber] == 0) {
                lastLastTime[lastNumber] = i + 1;
            }
            else {
                lastLastTime[lastNumber] = lastTime[lastNumber];
            }
            lastTime[lastNumber] = i + 1;
        }
        return lastNumber;
    }
}

