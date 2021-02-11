/*
 * Copyright (C) 2021 José Carlos Palma <palmahn@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.jcpalma.aoc.day01;

import io.jcpalma.aoc.Day;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Solución para el día 1.
 * <pre>
 * --- Day 1: Report Repair ---
 * 1. Find the two entries that sum to 2020
 * 2. Find the three entries that sum to 2020
 * </pre>
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.1
 * @see <a href="https://adventofcode.com/2020/day/1">Advent of Code (Day 1: Report Repair)</a>
 */
public class Day01 extends Day {

    /**
     * Crea una instancia para la solición del día 1.
     */
    public Day01() {}
    
    /**
     * Ejecuta la solición para el día 1.
     */
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
