package com.fventura.aoc2020.days;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fventura.aoc2020.common.Day;

public class Day13 implements Day {

    private final String[] input;
    private final int timestamp;
    private Integer[] buses;
    final String[] parsedBuses;

    public Day13() {
        input = loadDayStrings(13);
        timestamp = Integer.parseInt(input[0]);
        parsedBuses = input[1].split(",");
        final Set<Integer> actualBuses = new LinkedHashSet<>();
        for (String bus : parsedBuses) {
            try {
                final int busInt = Integer.parseInt(bus);
                actualBuses.add(busInt);
            } catch (final NumberFormatException e) {
                //Ignore failed
            }
        }
        buses = actualBuses.toArray(Integer[]::new);
    }

    @Override
    public Object part1() {
        int currentWaitTime = 0;
        int currentBus = 0;
        for (int i = 0; i < buses.length; i++) {
            int roundedTime = Math.floorDiv(timestamp, buses[i]) * buses[i];
            if (roundedTime < timestamp) {
                roundedTime = roundedTime + buses[i];
            }
            int waitTime = roundedTime - timestamp;
            if (i == 0 || waitTime < currentWaitTime) {
                currentWaitTime = waitTime;
                currentBus = buses[i];
            }
        }
        return (int) (currentBus * currentWaitTime);
    }

    @Override
    public Object part2() {
        final int[] timestamps = new int[buses.length];
        int currentTimestamp = -1;
        int timestampIndex = 0;
        for (String parsedBus : parsedBuses) {
            currentTimestamp++;
            if (parsedBus.equals("x")) {
                continue;
            }
            timestamps[timestampIndex] = currentTimestamp;
            timestampIndex++;
        }
        long timestamp = buses[0];
        long lastTimestampMultiplier = buses[0];
        int i = 1;
        for (; ; timestamp = timestamp + lastTimestampMultiplier) {
            if ((timestamp + timestamps[i]) % buses[i] == 0) {
                lastTimestampMultiplier = lastTimestampMultiplier * buses[i];
                i++;
            }
            if(i == buses.length) {
                return timestamp;
            }
        }
    }
}

