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
package io.jcpalma.aoc.day04;

import io.jcpalma.aoc.Day;
import java.util.List;

/**
 * Solución para el día 4.
 * <pre>
 * --- Day 4: Passport Processing ---
 * 1. detecting which passports have all required fields.
 * 2. count the passports where all required fields are both present and valid according to the above rules.
 * </pre>
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.4
 * @see <a href="https://adventofcode.com/2020/day/4">Advent of Code (Day 4: Passport Processing)</a>
 */
public class Day04 extends Day {

    /**
     * Crea una instancia para la solición del día 4.
     */
    public Day04() {}
    
    
    /**
     * Ejecuta la solición para el día 4.
     */
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
