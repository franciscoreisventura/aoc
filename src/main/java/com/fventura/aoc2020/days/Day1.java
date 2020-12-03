package com.fventura.aoc2020.days;

import java.io.IOException;

import com.fventura.aoc2020.common.Day;

public class Day1 implements Day {


    @Override
    public String part1() throws IOException {
        long[] numbers = loadDayNumbers(1);

        for(int i = 0; i < numbers.length; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == 2020) {
                    return numbers[i] * numbers[j] + "";
                }
            }
        }
        return null;
    }

    @Override
    public String part2() throws IOException {
        long[] numbers = loadDayNumbers(1);

        for(int i = 0; i < numbers.length; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                for(int k = j+1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                        return numbers[i] * numbers[j] * numbers[k] + "";
                    }
                }
            }
        }
        return null;
    }
}
