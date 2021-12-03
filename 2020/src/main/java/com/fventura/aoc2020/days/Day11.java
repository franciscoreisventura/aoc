package com.fventura.aoc2020.days;

import java.util.Arrays;

import com.fventura.aoc2020.common.Day;

public class Day11 implements Day {

    private String[] seats;

    public Day11() {
        seats = loadDayStrings(11);
    }

    @Override
    public Object part1() {
        int occupiedSeats = 0;
        String[] newSeats = Arrays.copyOf(seats, seats.length);
        String[] lastRoundSeats = Arrays.copyOf(newSeats, newSeats.length);
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < newSeats.length; i++) {
                for (int j = 0; j < newSeats[i].length(); j++) {
                    char c = newSeats[i].charAt(j);
                    if (c == '.') {
                        continue;
                    }
                    StringBuilder builder = new StringBuilder(newSeats[i]);
                    int surroundingBusySeats = getSurroundingBusySeats(lastRoundSeats, i, j);
                    if (c == 'L' && surroundingBusySeats == 0) {
                        builder.setCharAt(j, '#');
                        occupiedSeats++;
                        changed = true;
                    } else if (c == '#' && surroundingBusySeats >= 4) {
                        builder.setCharAt(j, 'L');
                        occupiedSeats--;
                        changed = true;
                    }
                    newSeats[i] = builder.toString();
                }
            }
            lastRoundSeats = Arrays.copyOf(newSeats, newSeats.length);
        } while (changed);
        return occupiedSeats;
    }

    @Override
    public Object part2() {
        long occupiedSeats = 0;
        String[] newSeats = Arrays.copyOf(seats, seats.length);
        String[] lastRoundSeats = Arrays.copyOf(newSeats, newSeats.length);
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < newSeats.length; i++) {
                for (int j = 0; j < newSeats[i].length(); j++) {
                    char c = newSeats[i].charAt(j);
                    if (c == '.') {
                        continue;
                    }
                    StringBuilder builder = new StringBuilder(newSeats[i]);
                    int surroundingBusySeats = getSurroundingBusySeatsPart2(lastRoundSeats, i, j);
                    if (c == 'L' && surroundingBusySeats == 0) {
                        builder.setCharAt(j, '#');
                        occupiedSeats++;
                        changed = true;
                    } else if (c == '#' && surroundingBusySeats >= 5) {
                        builder.setCharAt(j, 'L');
                        occupiedSeats--;
                        changed = true;
                    }
                    newSeats[i] = builder.toString();
                }
            }
            lastRoundSeats = Arrays.copyOf(newSeats, newSeats.length);
        } while (changed);
        return occupiedSeats;
    }

    private int getSurroundingBusySeats(String[] seats, int i, int j) {
        int busySeats = 0;
        if (j > 0) {
            if (seats[i].charAt(j - 1) == '#') {
                busySeats++;
            }
            if (i > 0 && seats[i - 1].charAt(j - 1) == '#') {
                busySeats++;
            }
        }
        if (j < seats[i].length() - 1) {
            if (seats[i].charAt(j + 1) == '#') {
                busySeats++;
            }
            if (i > 0 && seats[i - 1].charAt(j + 1) == '#') {
                busySeats++;
            }
        }
        if (i < seats.length - 1) {
            if (seats[i + 1].charAt(j) == '#') {
                busySeats++;
            }
            if (j < seats[i].length() - 1 && seats[i + 1].charAt(j + 1) == '#') {
                busySeats++;
            }
            if (j > 0 && seats[i + 1].charAt(j - 1) == '#') {
                busySeats++;
            }
        }
        if (i > 0 && seats[i - 1].charAt(j) == '#') {
            busySeats++;
        }
        return busySeats;
    }

    private int getSurroundingBusySeatsPart2(String[] seats, int i, int j) {
        int busySeats = 0;
        char seat;
        int j2 = j;
        int i2 = i;
        while (j2 > 0) {
            seat = seats[i].charAt(j2 - 1);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            j2--;
        }
        j2 = j;
        while (j2 > 0 && i2 > 0) {
            seat = seats[i2 - 1].charAt(j2 - 1);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            j2--;
            i2--;
        }
        j2 = j;
        while (j2 < seats[i].length() - 1) {
            seat = seats[i].charAt(j2 + 1);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            j2++;
        }
        j2 = j;
        i2 = i;
        while (j2 < seats[i].length() - 1 && i2 > 0) {
            seat = seats[i2 - 1].charAt(j2 + 1);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            j2++;
            i2--;
        }
        i2 = i;
        while (i2 < seats.length - 1) {
            seat = seats[i2 + 1].charAt(j);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            i2++;
        }
        i2 = i;
        j2 = j;
        while (i2 < seats.length - 1 && j2 < seats[i2].length() - 1) {
            seat = seats[i2 + 1].charAt(j2 + 1);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            i2++;
            j2++;
        }
        i2 = i;
        j2 = j;
        while (i2 < seats.length - 1 && j2 > 0) {
            seat = seats[i2 + 1].charAt(j2 - 1);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            i2++;
            j2--;
        }
        i2 = i;
        while (i2 > 0) {
            seat = seats[i2 - 1].charAt(j);
            if (seat == '#') {
                busySeats++;
                break;
            }
            if (seat == 'L') {
                break;
            }
            i2--;
        }
        return busySeats;
    }
}
