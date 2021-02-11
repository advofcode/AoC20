/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day04;

import java.util.Set;

/**
 *
 * @author jcpalma
 */
public class Field {

    
    private final boolean required;
    private final boolean valid;
    private final String key;
    private final String value;

    public Field(String key, String value, boolean required) {
        this.key = key;
        this.required = required;
        this.value = value;
        this.valid = checkRules();
    }

    public String key() {
        return key;
    }

    public String value() {
        return this.value;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isValid() { 
        return valid;
    }

    public boolean isNotValid() {
        return !valid;
    }

    @Override
    public String toString() {
        return String.format("{%s%s = %s}", key, required ? "*" : " ", value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Field)) {
            return false;
        }

        Field other = (Field) obj;
        return key.equals(other.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
    
    private boolean checkRules() {
        switch (key) {
            case "byr":
                return byrIsValid();
            case "iyr":
                return iyrIsValid();
            case "eyr":
                return eyrIsValid();
            case "hgt":
                return hgtIsValid();
            case "hcl":
                return value.matches("^[#][0-9a-f]{6}$");
            case "ecl":
                return value.matches("^(amb|blu|brn|gry|grn|hzl|oth)$");
            case "pid":
                return value.matches("^[0-9]{9}$");
            default:
                return true;
        }
    }

    private boolean byrIsValid() {
        int year = 0;
        try {
            year = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return year >= 1920 && year <= 2002;
    }

    private boolean iyrIsValid() {
        int year = 0;
        try {
            year = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return year >= 2010 && year <= 2020;
    }

    private boolean eyrIsValid() {
        int year = 0;
        try {
            year = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return year >= 2020 && year <= 2030;
    }

    private boolean hgtIsValid() {
        int height = 0;
        if (!value.matches("^[0-9]+(cm|in)$")) {
            return false;
        }

        try {
            height = Integer.parseInt(value.replaceFirst("cm|in", ""));
        } catch (RuntimeException ex) {
            return false;
        }

        return value.endsWith("cm") ? (height >= 150 && height <= 193) : (height >= 59 && height <= 76);
    }

}
