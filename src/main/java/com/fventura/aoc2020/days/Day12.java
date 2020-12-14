package com.fventura.aoc2020.days;

import java.util.Arrays;

import com.fventura.aoc2020.common.Day;

public class Day12 implements Day {

    private String[] instructions;

    public Day12() {
        instructions = loadDayStrings(12);
    }

    @Override
    public Object part1() {
        //NESW
        final int[] movement = {0,0,0,0};
        int currDir = 1;
        for (String instruction : instructions) {
            char direction = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));
            switch (direction) {
                case 'F': {
                    movement[currDir] += value;
                    break;
                }
                case 'N': {
                    movement[0] += value;
                    break;
                }
                case 'E': {
                    movement[1] += value;
                    break;
                }
                case 'S': {
                    movement[2] += value;
                    break;
                }
                case 'W': {
                    movement[3] += value;
                    break;
                }
                case 'L': {
                    int rotation = value / 90;
                    currDir = currDir - rotation;
                    while (currDir < 0) {
                        currDir = currDir + 4;
                    }
                    break;
                }
                case 'R': {
                    int rotation = value / 90;
                    currDir = currDir + rotation;
                    if(currDir >= 4) {
                        currDir = currDir % 4;
                    }
                    break;
                }
            }
        }
        return Math.abs(movement[2] - movement[0]) + Math.abs(movement[3] - movement[1]);
    }

    @Override
    public Object part2() {
        //NESW
        int[] waypoint = {1, 10, 0, 0};
        final int[] movement = {0,0,0,0};
        for (String instruction : instructions) {
            char direction = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));
            switch (direction) {
                case 'F': {
                    movement[0] += waypoint[0] * value;
                    movement[1] += waypoint[1] * value;
                    movement[2] += waypoint[2] * value;
                    movement[3] += waypoint[3] * value;
                    break;
                }
                case 'N': {
                    waypoint[0] += value;
                    break;
                }
                case 'E': {
                    waypoint[1] += value;
                    break;
                }
                case 'S': {
                    waypoint[2] += value;
                    break;
                }
                case 'W': {
                    waypoint[3] += value;
                    break;
                }
                case 'L': {
                    int rotation = value / 90;
                    final int[] newWaypoint = Arrays.copyOf(waypoint, waypoint.length);
                    for(int i = 0; i<4; i++) {
                        int currDir = i - rotation;
                        while (currDir < 0) {
                            currDir = currDir + 4;
                        }
                        newWaypoint[currDir] = waypoint[i];
                    }
                    waypoint = newWaypoint;
                    break;
                }
                case 'R': {
                    int rotation = value / 90;
                    final int[] newWaypoint = Arrays.copyOf(waypoint, waypoint.length);
                    for(int i = 0; i<4; i++) {
                        int currDir = i + rotation;
                        if(currDir >= 4) {
                            currDir = currDir % 4;
                        }
                        newWaypoint[currDir] = waypoint[i];
                    }
                    waypoint = newWaypoint;
                }
            }
        }
        return Math.abs(movement[2] - movement[0]) + Math.abs(movement[3] - movement[1]);
    }
}

