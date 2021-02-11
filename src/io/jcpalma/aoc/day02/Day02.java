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
package io.jcpalma.aoc.day02;

import io.jcpalma.aoc.Day;
import java.util.List;
import java.util.stream.Stream;

/**
 * Solución para el día 2.
 * <pre>
 * --- Day 2: Password Philosophy ---
 * 1. How many passwords are valid according to their policies?
 * 2. How many passwords are valid according to the new interpretation of the policies?
 * </pre>
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.2
 * @see <a href="https://adventofcode.com/2020/day/2">Advent of Code (Day 2:
 * Password Philosophy)</a>
 */
public class Day02 extends Day {

    /**
     * Crea una instancia para la solición del día 2.
     */
    public Day02() {
    }

    /**
     * Ejecuta la solición para el día 2.
     */
    @Override
    public void run() {

        System.out.println("--- Day 2: Password Philosophy ---");

        List<String> pass = inputToList();

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        long result = pass.stream().flatMap(p -> Stream.of(new Password(p)))
                .filter(Password::complyPolicy)
                .count();
        System.out.println("  My puzzle answer was: " + result);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        result = pass.stream().flatMap(p -> Stream.of(new Password(p)))
                .filter(Password::complyNewPolicy)
                .count();
        System.out.println("  My puzzle answer was: " + result);

    }

    /**
     * Clase que representa una contraseña.
     *
     * @author José Carlos Palma {@literal <palmahn@gmail.com>}
     * @version v2020.2
     */
    public static class Password {

        private final String times;
        private final String letter;
        private final String pass;

        /**
         * Crea una instancia de Password según la política.
         *
         * @param line es una contraseña y su política.
         */
        public Password(String line) {
            String[] data = line.split("([ ]|[:])", 3);
            this.times = data[0];
            this.letter = data[1];
            this.pass = data[2].trim();
        }

        /**
         * Indica si la contraseña cumple con la política.
         *
         * @return true si cumple.
         */
        public boolean complyPolicy() {
            String[] x = times.split("-");
            int li = Integer.parseInt(x[0]);
            int ls = Integer.parseInt(x[1]);
            int l = this.letter.charAt(0);

            int count = 0;
            for (Character c : pass.toCharArray()) {
                if (c == l) {
                    count++;
                }
            }

            return count >= li && count <= ls;
        }

        /**
         * Indica si la contraseña cumple con la nueva política.
         *
         * @return true si cumple.
         */
        public boolean complyNewPolicy() {
            String[] x = times.split("-");
            int li = Integer.parseInt(x[0]) - 1;
            int ls = Integer.parseInt(x[1]) - 1;
            int c = this.letter.charAt(0);

            return 1 == (pass.charAt(li) == c ? 1 : 0) + (pass.charAt(ls) == c ? 1 : 0);
        }

        @Override
        public String toString() {
            return String.format("%s %s: %s", times, letter, pass);
        }

    }

}
