package com.fventura.aoc2020.days;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.fventura.aoc2020.common.Day;

public class Day7 implements Day {

    final Pattern containShinyGold = Pattern.compile(".*[1-9]+ shiny gold.*");
    final Pattern shinyGoldContains = Pattern.compile("shiny gold bags contain [1-9]+.*");
    final String[] rules;

    public Day7() {
        rules = loadDayStrings(7);
    }


    @Override
    public Object part1() {
        return getOuterBags(containShinyGold).size();
    }

    @Override
    public Object part2() {
        return getInnerBags(shinyGoldContains) - 1;
    }

    private Set<String> getOuterBags(final Pattern bagPattern) {
        final Set<String> biggerBags = new HashSet<>();
        for (final String bag : rules) {
            if (bagPattern.matcher(bag).matches()) {
                final String[] color = bag.split(" contain ")[0].split(" ");
                final String biggerBag = color[0] + " " + color[1];
                biggerBags.add(biggerBag);
                biggerBags.addAll(getOuterBags(Pattern.compile(".*[1-9]+ " + biggerBag + ".*")));
            }
        }
        return biggerBags;
    }

    private int getInnerBags(final Pattern bagPattern) {
        int innerBags = 1;
        for (final String bag : rules) {
            if (bagPattern.matcher(bag).matches()) {
                final String[] innerColors = bag.split(" contain ")[1].split(", ");
                for (String innerColor : innerColors) {
                    final String[] splitted = innerColor.split(" ");
                    final int multiplier = Integer.parseInt(splitted[0]);
                    final String color = splitted[1] + " " + splitted[2];
                    final Pattern innerPattern = Pattern.compile(color + " bags contain [1-9].*");
                    innerBags += multiplier * getInnerBags(innerPattern);
                }
            }
        }
        return innerBags;
    }
}
