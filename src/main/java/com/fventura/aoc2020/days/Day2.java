package com.fventura.aoc2020.days;

import java.io.IOException;

import com.fventura.aoc2020.common.Day;

public class Day2 implements Day {


    @Override
    public Object part1() throws IOException {
        final String[] passwordObjectList = loadDayStrings(2);
        int valid = 0;

        for (final String passwordObject : passwordObjectList) {
            if(isValidPassword(passwordObject)) {
                valid++;
            }
        }
        return valid;
    }

    @Override
    public Object part2() throws IOException {
        final String[] passwordObjectList = loadDayStrings(2);
        int valid = 0;

        for (final String passwordObject : passwordObjectList) {
            if(isValidPassword2(passwordObject)) {
                valid++;
            }
        }
        return valid;
    }

    private boolean isValidPassword(String passwordObject) {
        final String[] parts = passwordObject.split(" ");
        final String[] range = parts[0].split("-");
        final int minRange = Integer.parseInt(range[0]);
        final int maxRange = Integer.parseInt(range[1]);
        final String letter = parts[1].charAt(0) + "";
        final String password = parts[2];
        final String checkPassword = password.replaceAll(letter, "");
        final int diff = password.length() - checkPassword.length();
        return diff >= minRange && diff <= maxRange;
    }

    private boolean isValidPassword2(String passwordObject) {
        final String[] parts = passwordObject.split(" ");
        final String[] indexes = parts[0].split("-");
        final int firstIndex = Integer.parseInt(indexes[0]);
        final int secondIndex = Integer.parseInt(indexes[1]);
        final char letter = parts[1].charAt(0);
        final String password = parts[2];
        int diff = 0;
        if (letter == password.charAt(firstIndex-1)) {
            diff++;
        }
        if (letter == password.charAt(secondIndex-1)) {
            diff++;
        }
        return diff == 1;
    }
}
