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
package io.jcpalma.aoc.day09;

import io.jcpalma.aoc.Day;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Solución para el día 9.
 * <pre>
 * --- Day 9: Encoding Error ---
 * 1. What is the first number that does not have this property?
 * 2. What is the encryption weakness in your XMAS-encrypted list of numbers?
 * </pre>
 *
 * @author José Carlos Palma {@literal palmahn@gmail.com}
 * @version v2020.9
 * @see <a href="https://adventofcode.com/2020/day/9">Advent of Code (Day 9:
 * Encoding Error)</a>
 */
public class Day09 extends Day {

    /**
     * Crea una instancia para la solición del día 9.
     */
    public Day09() {
    }

    /**
     * Ejecuta la solición para el día 9.
     */
    @Override
    public void run() {

        System.out.println("--- Day 9: Encoding Error ---");

        List<Long> input = streamInput().map(Long::parseLong).collect(Collectors.toList());

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");

        AtomicInteger indx = new AtomicInteger(24);

        Predicate<Long> sumNotFound = (number) -> {
            for (int i = indx.get() - 25; i < indx.get() - 1; i++) {
                for (int j = i + 1; j < indx.get(); j++) {
                    if (input.get(i) + input.get(j) - number == 0) {
                        return false;
                    }
                }
            }
            return true;
        };
        long result = input.stream()
                .skip(25)
                .peek(n -> indx.addAndGet(1))
                .filter(sumNotFound)
                .findAny().orElse(0L);

        System.out.printf("  My puzzle answer was: %d [index: %d]%n", result, indx.get());

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");

        LongSummaryStatistics stat = new LongSummaryStatistics();
        search:
        for (int i = 0; i < indx.get() - 1; i++) {
            long ac = input.get(i);
            for (int j = i + 1; j < indx.get(); j++) {
                ac += input.get(j);
                if (ac - result == 0) {
                    stat = input.subList(i, j+1).stream().mapToLong(Long::valueOf).summaryStatistics();
                    break search;
                } else if ( ac > result ) {
                    break;
                }
            }
        }

        System.out.printf("  My puzzle answer was: %d%n", stat.getMax() + stat.getMin());

    }

}
