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
package io.jcpalma.aoc.day06;

import io.jcpalma.aoc.Day;
import java.util.List;

/**
 * Solución para el día 6.
 * <pre>
 * --- Day 6: Custom Customs ---
 * 1. count the number of questions to which anyone answered "yes".
 * 2. Count the number of questions to which everyone answered "yes".
 * </pre>
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.6
 * @see <a href="https://adventofcode.com/2020/day/6">Advent of Code (Day 6: Custom Customs)</a>
 */
public class Day06 extends Day {

    /**
     * Crea una instancia para la solición del día 6.
     */
    public Day06() {}
    
    
    /**
     * Ejecuta la solición para el día 6.
     */
    @Override
    public void run() {

        System.out.println("--- Day 6: Custom Customs ---");

        List<Group> groups = streamInput().collect(new GroupCollector());

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        int counts = groups.stream().mapToInt(Group::answeredYes).sum();
        System.out.printf("  My puzzle answer was: %d%n", counts);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        long result = groups.stream().mapToLong(Group::allAnsweredYes).sum();
        System.out.printf("  My puzzle answer was: %d%n", result);

    }

}
