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
package io.jcpalma.aoc.day03;

import io.jcpalma.aoc.Day;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * Solución para el día 3.
 * <pre>
 * --- Day 3: Toboggan Trajectory ---
 * 1. How many trees would you encounter?
 * 2. What do you get if you multiply together the number of trees encountered on each of the listed slopes?
 * </pre>
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.3
 * @see <a href="https://adventofcode.com/2020/day/3">Advent of Code (Day 3:
 * Toboggan Trajectory)</a>
 */
public class Day03 extends Day {

    /**
     * Crea una instancia para la solición del día 3.
     */
    public Day03() {
    }

    /**
     * Ejecuta la solición para el día 3.
     */
    @Override
    public void run() {

        System.out.println("--- Day 3: Toboggan Trajectory ---");

        List<String> input = inputToList();
        AtomicLong accum = new AtomicLong(1);

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        long trees = countTrees(3, 1, input, accum);
        System.out.println("  My puzzle answer was: " + trees);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        accum.set(1);
        System.out.printf("  Right 1, down 1: %,d%n", countTrees(1, 1, input, accum));
        System.out.printf("  Right 3, down 1: %,d%n", countTrees(3, 1, input, accum));
        System.out.printf("  Right 5, down 1: %,d%n", countTrees(5, 1, input, accum));
        System.out.printf("  Right 7, down 1: %,d%n", countTrees(7, 1, input, accum));
        System.out.printf("  Right 1, down 2: %,d%n", countTrees(1, 2, input, accum));

        System.out.printf("  My puzzle answer was: %d%n", accum.get());

    }
    
    /**
     * Cuenta la cantidad de árboles según la ruta.
     * @param right desplazamiento hacia la derecha.
     * @param down desplazamiento hacia abajo.
     * @param input es el bosque.
     * @param accum es el acumulador de cantidad de arboles.
     * @return la cantidad de árboles encontrados.
     */
    private long countTrees(int right, int down, List<String> input, AtomicLong accum) {
        AtomicInteger col = new AtomicInteger(right);

        long count = Stream.iterate(down, i -> i < input.size(), i -> i + down)
                .map(i -> input.get(i))
                .filter(s -> s.charAt(col.getAndAdd(right) % s.length()) == '#')
                .count();
        accum.getAndAccumulate(count, (a, b) -> a * b);
        return count;
    }

}
