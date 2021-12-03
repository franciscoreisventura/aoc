package com.fventura.aoc2020;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.fventura.aoc2020.common.Day;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IOException {
        final long startTime = System.nanoTime();
        for (int day = 1; day <= 18; day++) {
            System.out.println("Day " + day + ": ");
            Day instance = (Day) Class.forName("com.fventura.aoc2020.days.Day" + day).getDeclaredConstructor().newInstance();
            System.out.println(instance.part1());
            System.out.println(instance.part2());
        }
        final long totalTime = System.nanoTime() - startTime;
        System.out.println("Total time: " + totalTime / 1000000000 + "s");
    }
}
