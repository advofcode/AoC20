/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author jcpalma
 */
public class Passport {

    private static final Set<String> REQUIRED_FIELDS = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    private final List<Field> fields = new ArrayList<>();

    public Passport() {
    }

    public void addFields(String data) {
        Scanner s = new Scanner(data).useDelimiter("[ ]+");
        while (s.hasNext()) {
            String[] at = s.next().strip().split(":", 2);
            String key = at[0];
            String value = at[1];
            boolean required = Passport.isFieldRequired(key);
            fields.add(new Field(key, value, required));
        }
    }

    public boolean hasAllRequiredFields() {
        return fields.stream().filter(Field::isRequired).count() == REQUIRED_FIELDS.size();
    }

    public boolean hasAllDataRulesPass() {
        boolean hasInvalid = fields.stream()
                .filter(Field::isRequired)
                .anyMatch(Field::isNotValid);
        return !hasInvalid;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Fields: ")
                .append(fields.size())
                .append("\n")
                .append(fields)
                .toString();
    }

    private static boolean isFieldRequired(String key) {
        return REQUIRED_FIELDS.contains(key);
    }
}
