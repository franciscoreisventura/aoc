package com.fventura.aoc2020.common;

import static org.apache.commons.io.FileUtils.readFileToString;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public interface Day {

    Object part1();

    Object part2();

    default long[] loadDayNumbers(int day) {
        return Arrays.stream(day(day).split(System.lineSeparator())).mapToLong(Long::parseLong).toArray();
    }

    default String[] loadDayStrings(int day) {
        return Arrays.stream(day(day).split(System.lineSeparator())).toArray(String[]::new);
    }

    default String[] loadDayStrings(int day, String regex) {
        return Arrays.stream(day(day).split(regex)).toArray(String[]::new);
    }

    default String day(int day) {
        return getResourceAsString("day" + day + ".txt");
    }

    default String getResourceAsString(String resource) {
        try {
            return readFileToString(new File(Day.class.getClassLoader().getResource(resource).getFile()), "UTF-8");
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
