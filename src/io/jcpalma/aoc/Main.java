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
package io.jcpalma.aoc;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Soluciones para los pequeños rompecabezas de programacion de 'Advent of Code'
 * del 2020.
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.0
 * @see <a href="https://adventofcode.com/2020">Advent of Code 2020</a>
 */
public class Main {

    /**
     * Ejecución de la aplicación.
     *
     * @param args argumentos de línea de comandos.
     */
    public static void main(String[] args) {

        System.out.println("Welcome to 'Advent of Code'");

        final DecimalFormat df = new DecimalFormat("00");
        final Scanner read = new Scanner(System.in);

        do {
            System.out.println("--- Choose a day (type 'q' to quit) ---");
            System.out.print("Day: ");
            String cin = args.length > 0 ? args[0] : read.nextLine().strip();

            if (cin.matches("^[1-9]|1[0-9]|2[0-5]$")) {
                try {
                    String d = df.format(Integer.parseInt(cin));
                    Class<?> dayClass = Class.forName(String.format("io.jcpalma.aoc.day%s.Day%s", d, d));
                    Day day = (Day) dayClass.getDeclaredConstructor().newInstance();
                    Method runMethod = dayClass.getDeclaredMethod("run");
                    runMethod.invoke(day);
                    System.out.println("");
                } catch (ClassNotFoundException ex) {
                    System.out.println("[Warning] Day not completed.");
                    continue;
                } catch (Exception ex) {
                }
            }

            if (cin.toLowerCase().matches("q|quit|exit") || args.length > 0) {
                break;
            }

        } while (true);

    }

}
