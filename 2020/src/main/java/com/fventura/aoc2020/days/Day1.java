package com.fventura.aoc2020.days;

import com.fventura.aoc2020.common.Day;

public class Day1 implements Day {

    final long[] numbers;

    public Day1() {
        numbers = loadDayNumbers(1);
    }


    @Override
    public Object part1() {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == 2020) {
                    return numbers[i] * numbers[j];
                }
            }
        }
        return null;
    }

    @Override
    public Object part2()  {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                for(int k = j+1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                        return numbers[i] * numbers[j] * numbers[k];
                    }
                }
            }
        }
        return null;
    }
}
