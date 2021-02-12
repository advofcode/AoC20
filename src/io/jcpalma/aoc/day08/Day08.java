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
package io.jcpalma.aoc.day08;

import io.jcpalma.aoc.Day;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Solución para el día 8.
 * <pre>
 * --- Day 8: Handheld Halting ---
 * 1. what value is in the accumulator?
 * 2. fix infinite loop and What is the value of the accumulator after the program terminates?
 * </pre>
 *
 * @author José Carlos Palma {@literal palmahn@gmail.com}
 * @version v2020.8
 * @see <a href="https://adventofcode.com/2020/day/8">Advent of Code (Day 8:
 * Handheld Halting)</a>
 */
public class Day08 extends Day {

    /**
     * Crea una instancia para la solición del día 8.
     */
    public Day08() {
    }

    /**
     * Ejecuta la solición para el día 8.
     */
    @Override
    public void run() {

        System.out.println("--- Day 8: Handheld Halting ---");

        List<Instruction> program = streamTest()
                .map(Instruction::new)
                //.peek(System.out::println)
                .collect(Collectors.toList());

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        AtomicInteger accum = new AtomicInteger(0);
        execute(program, accum, 0);
        System.out.printf("  My puzzle answer was: %d%n", accum.get());

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        fixAndExecute(program, accum);
        System.out.printf("  My puzzle answer was: %d%n", accum.get());

    }

    /**
     * Ejecuta el código según el archivo con las instrucciones.
     *
     * @param program lista de instrucciones a ejecutar.
     * @param ac es el registro acumulador.
     * @param pointer es el apuntador a la instrucción inicial.
     * @return 0 si la ejecución fue "exitosa" (sin ciclo infinito).
     */
    public int execute(final List<Instruction> program, final AtomicInteger ac, int pointer) {
        Set<Instruction> stack = new HashSet<>();
        Instruction instruction = program.get(pointer);
        while (!stack.contains(instruction)) {
            switch (instruction.operation) {
                case "jmp":
                    pointer += instruction.argument;
                    break;
                case "acc":
                    ac.addAndGet(instruction.argument);
                default:
                    pointer++;
            }
            stack.add(instruction);
            if (pointer >= program.size()) {
                break;
            }
            instruction = program.get(pointer);
        }
        stack.clear();
        return pointer - program.size();
    }

    /**
     * Ejecuta el código según el archivo con las instrucciones, en caso que
     * exista un ciclo infinito trata de corregir intercambiando las operaciones
     * <code>jmp</code> y <code>nop</code> entre si.
     *
     * @param program lista de instrucciones a ejecutar.
     * @param ac es el registro acumulador.
     * @return 0 si la ejecución fue "exitosa" (sin ciclo infinito).
     */
    public int fixAndExecute(final List<Instruction> program, final AtomicInteger ac) {
        ac.set(0);
        int pointer = 0;
        Set<Instruction> stack = new HashSet<>();
        Instruction instruction = program.get(pointer);

        while (!stack.contains(instruction)) {
            AtomicInteger $ac = new AtomicInteger(ac.get());
            int status = 0;
            switch (instruction.operation) {
                case "acc":
                    ac.addAndGet(instruction.argument);
                    pointer++;
                    break;
                case "jmp":
                case "nop":
                    instruction.swap();
                    status = execute(program, $ac, pointer);
                    if (status == 0) {
                        ac.set($ac.get());
                        return 0;
                    }
                    instruction.swap();
                    pointer += instruction.operation.endsWith("jmp") ? instruction.argument : 1;

            }
            stack.add(instruction);
            if (pointer >= program.size()) {
                break;
            }
            instruction = program.get(pointer);
        }
        stack.clear();
        return pointer - program.size();
    }

    /**
     * Clase que representa una instrucción (operador y argumento).
     *
     * @author José Carlos Palma {@literal palmahn@gmail.com}
     * @version v2020.8
     * @see <a href="https://adventofcode.com/2020/day/8">Advent of Code (Day 8:
     * Handheld Halting)</a>
     */
    public static class Instruction {

        public static AtomicInteger COUNTER = new AtomicInteger();

        public String operation;
        public final int argument;
        private final int id = COUNTER.getAndIncrement();

        /**
         * Crea una instancia a partir de una instrucció.
         *
         * @param instruction es una línea de código
         */
        public Instruction(String instruction) {
            Scanner scan = new Scanner(instruction).useDelimiter(" ");
            this.operation = scan.next();
            this.argument = Integer.parseInt(scan.next());
        }

        /**
         * Intercambia las operaciones jmp o nop entre si.
         */
        public void swap() {
            switch (operation) {
                case "jmp":
                    operation = "nop";
                    break;
                case "nop":
                    operation = "jmp";
                    break;
            }
        }

        /**
         * Devuelve una cadena con la representación de la instrucción.
         *
         * @return un cadena id: operación argumento
         */
        @Override
        public String toString() {
            return String.format("%3d: %s %+3d", id, operation, argument);
        }

        /**
         * Indica si dos operaciones son iguales según el identificador de la
         * intrucción.
         *
         * @param obj es una instrucción.
         * @return true si ambas intrucciónes tienen el mismo identificador.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof Instruction)) {
                return false;
            }

            Instruction other = (Instruction) obj;
            return id == other.id;
        }

    }

}
