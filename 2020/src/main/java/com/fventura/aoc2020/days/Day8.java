package com.fventura.aoc2020.days;

import java.util.Arrays;

import com.fventura.aoc2020.common.Day;

public class Day8 implements Day {

    final String[] stack;

    public Day8() {
        stack = loadDayStrings(8);
    }

    @Override
    public Object part1() {
        return accumulate(stack)[0];
    }

    @Override
    public Object part2() {
        for (int i = 0; i<stack.length; i++) {
            final String instruction = stack[i];
            final String[] copy = Arrays.copyOf(stack, stack.length);
            switch (instruction.split(" ")[0]) {
                case "acc":
                    continue;
                case "nop":
                    copy[i] = instruction.replace("nop", "jmp");
                    break;
                case "jmp":
                    copy[i] = instruction.replace("jmp", "nop");
                    break;
                default:
                    break;
            }
            int[] result = accumulate(copy);
            if(result[1] == stack.length) {
                return result[0];
            }
        }
        return null;
    }

    private int[] accumulate(final String[] stack) {
        final boolean[] executed = new boolean[stack.length];
        int accumulator = 0;
        int pointer = 0;
        while (true) {
            if (pointer == stack.length || executed[pointer]) {
                return new int[]{accumulator, pointer};
            }
            executed[pointer] = true;
            final String current = stack[pointer];
            final String[] instruction = current.split(" ");
            final int signal = instruction[1].charAt(0) == '-' ? -1 : 1;
            final int operationValue = signal * Integer.parseInt(instruction[1].substring(1));
            switch (instruction[0]) {
                case "acc":
                    accumulator += operationValue;
                    break;
                case "jmp":
                    pointer += operationValue;
                    continue;
                default:
                    break;
            }
            pointer++;
        }
    }
}
