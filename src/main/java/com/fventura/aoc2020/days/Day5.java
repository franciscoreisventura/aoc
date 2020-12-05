package com.fventura.aoc2020.days;

import java.util.Arrays;

import com.fventura.aoc2020.common.Day;

public class Day5 implements Day {

    private final String[] bPasses;
    final int [] seatIds;

    public Day5() {
        bPasses = loadDayStrings(5);
        seatIds = new int[bPasses.length];
    }

    @Override
    public Object part1() {
        int seatId = 0;

        for(int i = 0; i < bPasses.length; i++) {
            String bPass = bPasses[i];
            final int row = parseBinarySpace(0, 127, bPass.substring(0, 7), 'B');
            final int column = parseBinarySpace(0, 7, bPass.substring(7), 'R');
            final int localSeatId = row * 8 + column;
            seatIds[i] = localSeatId;
            if (seatId < localSeatId) {
                seatId = localSeatId;
            }
        }
        return seatId;
    }

    private int parseBinarySpace(int min, int max, String path, char topHalf) {
        if (path.charAt(0) == topHalf) {
            min = max - (int) Math.floor((max - min)/2);
        }
        else {
            max = min + (int) Math.floor((max - min)/2);
        }
        if (path.length() == 1) {
            return path.charAt(0) == topHalf ? min : max;
        }
        return parseBinarySpace(min, max, path.substring(1), topHalf);
    }

    @Override
    public Object part2() {
        Arrays.sort(seatIds);
        for (int i = 0; i < seatIds.length-1; i++) {
            if(seatIds[i+1] - seatIds[i] == 2) {
                return seatIds[i] + 1;
            }
        }
        return null;
    }
}
