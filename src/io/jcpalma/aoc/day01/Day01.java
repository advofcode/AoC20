/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day01;

import io.jcpalma.aoc.Day;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author jcpalma
 */
public class Day01 extends Day {

    @Override
    public void run() {

        System.out.println("--- Day 1: Report Repair ---");

        List<Integer> entries = streamInput()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        long result = entries.stream()
                .flatMap(a -> entries.stream()
                    .filter(b -> a + b == 2020)
                    .limit(1)
                    .flatMap(b -> Stream.of(Arrays.asList(a, b)))
                )
                .limit(1)
                .peek(e -> System.out.printf("  %s\n", e))
                .flatMap(l -> l.stream())
                .reduce(1, (a, b) -> a * b);

        System.out.println("  My puzzle answer was: " + result);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        result = entries.stream()
                .flatMap(a -> entries.stream()
                    .flatMap(b -> entries.stream()
                        .filter(c -> a + b + c == 2020)
                        .limit(1)
                        .flatMap(c -> Stream.of(Arrays.asList(a, b, c)))
                    )
                )
                .limit(1)
                .peek(e -> System.out.printf("  %s\n", e))
                .flatMap(l -> l.stream())
                .reduce(1, (a, b) -> a * b);

        System.out.println("  My puzzle answer was: " + result);

    }

}
