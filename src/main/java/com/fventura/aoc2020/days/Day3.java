package com.fventura.aoc2020.days;

import java.io.IOException;

import com.fventura.aoc2020.common.Day;

public class Day3 implements Day {

    final char tree = '#';

    @Override
    public String part1() throws IOException {
        final String[] ride = loadDayStrings(3);

        return crashes(ride, 3,1) + "";
    }

    @Override
    public String part2() throws IOException {
        final String[] ride = loadDayStrings(3);

        return crashes(ride, 1,1)
                * crashes(ride, 3, 1)
                * crashes(ride, 5, 1)
                * crashes(ride, 7, 1)
                * crashes(ride, 1, 2) + "";

    }

    private int crashes(final String[] ride, final int right, final int down) {
        int crashes = 0;

        for(int i=0,j=0; i<ride.length; i = i + down,j = j + right) {
            final String slope = ride[i];
            if(hit(slope,j)) {
                crashes++;
            }
        }
        return crashes;
    }

    private boolean hit(final String slope, int j) {
        if(j >= slope.length()) {
            j = j % slope.length();
        }
        return tree == slope.charAt(j);
    }
}
