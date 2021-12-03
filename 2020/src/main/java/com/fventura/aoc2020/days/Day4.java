package com.fventura.aoc2020.days;

import java.util.regex.Pattern;

import com.fventura.aoc2020.common.Day;

public class Day4 implements Day {

    final String[] passports;
    final Pattern hairColorPattern = Pattern.compile("#[0-9a-f]{6}");
    final Pattern eyeColorPattern = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth");
    final Pattern idNumberPattern = Pattern.compile("[0-9]{9}");

    public Day4() {
        passports = loadDayStrings(4, "\\n\\s");
    }

    @Override
    public Object part1() {
        final String[] mandatoryFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        int valid = 0;
        for (final String passport : passports) {
            if (isValid(passport, mandatoryFields)) {
                valid++;
            }
        }
        return valid;
    }

    @Override
    public Object part2() {
        final String[] mandatoryFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        int valid = 0;
        for (final String passport : passports) {
            if (isValid(passport, mandatoryFields)) {
                final String[] fields = passport.split("\\s");
                if (validateFields(fields)) {
                    valid++;
                }
            }
        }
        return valid;
    }

    private boolean isValid(String passport, String[] mandatoryFields) {
        for (String mandatoryField : mandatoryFields) {
            if (!passport.contains(mandatoryField + ":")) {
                return false;
            }
        }
        return true;
    }

    private boolean validateFields(String[] fields) {
        for (final String field : fields) {
            final String[] fieldValue = field.split(":");
            switch (fieldValue[0]) {
                case "byr":
                    if (!isDateValid(fieldValue[1], 1920, 2002)) {
                        return false;
                    }
                    break;
                case "iyr":
                    if (!isDateValid(fieldValue[1], 2010, 2020)) {
                        return false;
                    }
                    break;
                case "eyr":
                    if (!isDateValid(fieldValue[1], 2020, 2030)) {
                        return false;
                    }
                    break;
                case "hgt":
                    try {
                        final int value = Integer.parseInt(fieldValue[1].substring(0, fieldValue[1].length() - 2));
                        final String unit = fieldValue[1].substring(fieldValue[1].length() - 2);
                        if (unit.equals("cm")) {
                            if (value < 150 || value > 193) {
                                return false;
                            }
                        } else if (unit.equals("in")) {
                            if (value < 59 || value > 76) {
                                return false;
                            }
                        } else return false;
                    } catch (final NumberFormatException e) {
                        return false;
                    }
                    break;
                case "hcl": {
                    if (!hairColorPattern.matcher(fieldValue[1]).matches()) {
                        return false;
                    }
                    break;
                }
                case "ecl": {
                    if (!eyeColorPattern.matcher(fieldValue[1]).matches()) {
                        return false;
                    }
                    break;
                }
                case "pid": {
                    if (!idNumberPattern.matcher(fieldValue[1]).matches()) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean isDateValid(final String date, final int start, final int end) {
        try {
            final int dateInt = Integer.parseInt(date);
            return dateInt >= start && dateInt <= end;
        } catch (final NumberFormatException e) {
        }
        return false;
    }
}
