/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day04;

import io.jcpalma.aoc.Day;
import java.util.List;

/**
 *
 * @author jcpalma
 */
public class Day04 extends Day {

    @Override
    public void run() {

        System.out.println("--- Day 4: Passport Processing ---");

        List<String> input = inputToList();
        List<Passport> passports = input.stream().collect(new PassportsCollector());

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        long result = passports.stream()
                .filter(Passport::hasAllRequiredFields)
                .count();
        System.out.printf("  My puzzle answer was: %d%n", result);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        result = passports.stream()
                .filter(Passport::hasAllRequiredFields)
                .filter(Passport::hasAllDataRulesPass)
                .count();
        System.out.printf("  My puzzle answer was: %d%n", result);

    }

}
