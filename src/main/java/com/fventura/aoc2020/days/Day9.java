package com.fventura.aoc2020.days;

import java.util.Arrays;

import com.fventura.aoc2020.common.Day;

public class Day9 implements Day {

    private final int preambleSize = 25;
    private final long[] values;

    public Day9() {
        values = loadDayNumbers(9);
    }

    @Override
    public Object part1() {
        return findInvalidNumber();
    }

    @Override
    public Object part2() {
        final long invalidNumber = findInvalidNumber();
        for (int i = 0; i<values.length; i++) {
            long sum = values[i];
            for (int j = i+1; j<values.length; j++) {
                sum += values[j];
                if (invalidNumber == sum) {
                    long[] result = Arrays.copyOfRange(values, i, j);
                    Arrays.sort(result);
                    return result[0] + result[result.length-1];
                }
                if (sum > invalidNumber) {
                    break;
                }
            }
        }
        return null;
    }

    private long findInvalidNumber() {
        for(int i = preambleSize; i<values.length; i++) {
            long currentValue = values[i];
            boolean found = false;
            for (int j = i-preambleSize; j<i; j++) {
                for (int k = j+1; k<i; k++) {
                    if(values[j] != values[k] && values[j] + values[k] == currentValue) {
                        found = true;
                        break;
                    }
                }
                if(found) {
                    break;
                }
            }
            if(!found) {
                return currentValue;
            }
        }
        return 0;
    }
}
