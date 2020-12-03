package com.fventura.aoc2020.common;

import static org.apache.commons.io.FileUtils.readFileToString;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public interface Day {

    String part1() throws IOException;
    String part2() throws IOException;

    default long[] loadDayNumbers(int day) throws IOException {
        return Arrays.stream(day(day).split(System.lineSeparator())).mapToLong(Long::parseLong).toArray();
    }

    default String[] loadDayStrings(int day) throws IOException {
        return Arrays.stream(day(day).split(System.lineSeparator())).toArray(String[]::new);
    }

    default String day(int day) throws IOException {
        return getResourceAsString("day" + day + ".txt");
    }

    default String getResourceAsString(String resource) throws IOException {
        return readFileToString(new File(Day.class.getClassLoader().getResource(resource).getFile()), "UTF-8");
    }
}
