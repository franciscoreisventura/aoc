package com.fventura.aoc2020.days;

import java.util.HashMap;
import java.util.Map;

import com.fventura.aoc2020.common.Day;

public class Day14 implements Day {

    private final String[] input;

    public Day14() {
        input = loadDayStrings(14);
    }

    @Override
    public Object part1() {
        char[] currentMask = {};
        final Map<Integer, String> memory = new HashMap<>();
        for (String instruction : input) {
            if (instruction.contains("mask")) {
                currentMask = instruction.split(" = ")[1].toCharArray();
                continue;
            }
            final String[] split = instruction.split(" = ");
            final Integer memoryField = Integer.parseInt(split[0].substring(4, split[0].length()-1));
            final Long valueToMask = Long.parseLong(split[1]);
            memory.put(memoryField, maskValue(valueToMask, currentMask));
        }
        Long sum = 0L;
        for(String current : memory.values()) {
            sum = sum + Long.parseLong(current, 2);
        }
        return sum;
    }

    private String maskValue(Long valueToMask, final char[] bitmask) {
        final char[] binary = String.format("%36s", Long.toBinaryString(valueToMask)).replace(' ', '0').toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bitmask.length; i++) {
            if (bitmask[i] == 'X') {
                result.append(binary[i]);
            }
            else {
                result.append(bitmask[i]);
            }
        }
        return result.toString();
    }

    @Override
    public Object part2() {
        char[] currentMask = {};
        final HashMap<Long, Long> memory = new HashMap<>();
        for (String instruction : input) {
            if (instruction.contains("mask")) {
                currentMask = instruction.split(" = ")[1].toCharArray();
                continue;
            }
            final String[] split = instruction.split(" = ");
            final Long memoryField = Long.parseLong(split[0].substring(4, split[0].length()-1));
            final Long value = Long.parseLong(split[1]);
            memory.putAll(maskValues(memoryField, currentMask, value));
        }
        return memory.values().stream().reduce(0L, Long::sum);
    }

    private HashMap<Long, Long> maskValues(final Long memoryAddress, final char [] bitmask, final Long value) {
        final char[] binary = String.format("%36s", Long.toBinaryString(memoryAddress)).replace(' ', '0').toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bitmask.length; i++) {
            switch (bitmask[i]) {
                case 'X':
                case '1':
                    result.append(bitmask[i]);
                    break;
                default:
                    result.append(binary[i]);
            }
        }
        return generateFloatingValues(result.toString(), value);
    }

    private HashMap<Long, Long> generateFloatingValues(final String binaryString, final Long value) {
        HashMap<Long, Long> values = new HashMap<>();
        int index = binaryString.indexOf('X');
        if (index == -1) {
            values.put(Long.parseLong(binaryString, 2), value);
            return values;
        }
        final String zeroPath = binaryString.replaceFirst("X", "0");
        final String onePath = binaryString.replaceFirst("X", "1");
        values.putAll(generateFloatingValues(zeroPath, value));
        values.putAll(generateFloatingValues(onePath, value));
        return values;
    }
}

