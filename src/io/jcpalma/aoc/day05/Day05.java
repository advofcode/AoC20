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
package io.jcpalma.aoc.day05;

import io.jcpalma.aoc.Day;
import java.util.List;

/**
 * Solución para el día 5.
 * <pre>
 * --- Day 5: Binary Boarding ---
 * 1. What is the highest seat ID on a boarding pass?
 * 2. What is the ID of your seat?
 * </pre>
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.5
 * @see <a href="https://adventofcode.com/2020/day/5">Advent of Code (Day 5:
 * Binary Boarding)</a>
 */
public class Day05 extends Day {

    /**
     * Crea una instancia para la solición del día 5.
     */
    public Day05() {
    }

    /**
     * Ejecuta la solición para el día 5.
     */
    @Override
    public void run() {

        System.out.println("--- Day 5: Binary Boarding ---");

        List<String> input = inputToList();

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        int result = input.stream()
                .map(Seat::new)
                .map(Seat::getSeatId)
                .max(Integer::compare)
                .get();

        System.out.printf("  My puzzle answer was: %d%n", result);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        result = input.stream()
                .map(Seat::new)
                .map(Seat::getSeatId)
                .sorted()
                .reduce((a, b) -> b - a == 1 ? b : a)
                .get();
        System.out.printf("  My puzzle answer was: %d%n", result + 1);

    }

    /**
     * Representa un número de asiento de un avión.
     *
     * @author José Carlos Palma {@literal <palmahn@gmail.com>}
     * @version v2020.5
     */
    public static class Seat {

        private final String pass;

        /**
         * Crea una instancia de Seat con el su representación binaria.
         *
         * @param pass una cadena que específica la partición del espacio
         * binario del asiento.
         */
        public Seat(String pass) {
            this.pass = pass;
        }

        /**
         * Devuelve el número de asiento, según la fila y columna.
         *
         * @return número de asiento.
         */
        public int getSeatId() {
            return getRow() * 8 + getCol();
        }

        /**
         * Devuelve el número de la fila.
         *
         * @return número de fila.
         */
        private int getRow() {
            return encode(pass.substring(0, 7), 0, 127, 'F', 'B');
        }

        /**
         * Devuelve el número de la columna.
         *
         * @return número de columna.
         */
        private int getCol() {
            return encode(pass.substring(7), 0, 7, 'L', 'R');
        }

        /**
         * Decodifica el valor de la fila o columna. Para el caso de la fila,
         * 'F' indica que se toma del rango la mitad inferior, 'B' toma la mitad
         * superior del rango. Para las columnas, 'L' la mitad inferior y 'R' la
         * mitad superior.
         *
         * @param chars son los caracteres a decodificar.
         * @param ri rango inicial.
         * @param rf rango final.
         * @param lower carácter que representa la mitad inferior del rango.
         * @param upper carácter que representa la mitas superior del rango.
         * @return el número de fila o columna.
         */
        private int encode(String chars, int ri, int rf, char lower, char upper) {
            for (int i = 0; i < chars.length(); i++) {
                int half = (rf - ri + 1) / 2;
                if (chars.charAt(i) == lower) { // lower half
                    rf -= half;
                } else if (chars.charAt(i) == upper) { // upper half
                    ri += half;
                }
            }
            return ri;
        }
    }

}
