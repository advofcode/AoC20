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
package io.jcpalma.aoc.day07;

import io.jcpalma.aoc.Day;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Solución para el día 7.
 * <pre>
 * --- Day 7: Handy Haversacks ---
 * 1. How many bag colors can eventually contain at least one shiny gold bag?
 * 2. How many individual bags are required inside your single shiny gold bag?
 * </pre>
 *
 * @author José Carlos Palma {@literal palmahn@gmail.com}
 * @version v2020.7
 * @see <a href="https://adventofcode.com/2020/day/7">Advent of Code (Day 7: Handy Haversacks)</a>
 */
public class Day07 extends Day implements Collector<String, Map<String, Map<String, Integer>>, Map<String, Map<String, Integer>>> {

    /**
     * Crea una instancia para la solición del día 7.
     */
    public Day07() {
    }

    /**
     * Ejecuta la solición para el día 7.
     */
    @Override
    public void run() {

        System.out.println("--- Day 7: Handy Haversacks ---");

        Function<String, String> clean = (rule) -> {
            return rule.replace(".", "")
                    .replace(" bags", "")
                    .replace(" bag", "")
                    .replace(" contain", ",");
        };

        Map<String, Map<String, Integer>> bags = streamInput()
                .map(clean)
                .collect(this);

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        long distinc = findContainers(bags, "shiny gold")
                .stream()
                .distinct()
                .count();
        System.out.printf("  My puzzle answer was: %d%n", distinc);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        System.out.printf("  My puzzle answer was: %d%n", totalBags(bags, "shiny gold"));
    }

    /**
     * Busqueda recursiva de la lista de bolsas que pueden contener al menos una
     * bolsa del color indicado.
     *
     * @param bags es la colección de bolsas con sus reglas.
     * @param color es el color a buscar.
     * @return Una lista con todas las bolsas.
     */
    public List<String> findContainers(Map<String, Map<String, Integer>> bags, String color) {
        List<String> containers = new ArrayList<>();
        bags.entrySet()
                .stream()
                .filter(bag -> bag.getValue().containsKey(color))
                .map(Map.Entry::getKey)
                .peek(containers::add)
                .forEach(c -> containers.addAll(findContainers(bags, c)));
        return containers;
    }

    /**
     * Devuelve la cantidad total de bolsas contenidas en una bolsa particular.
     *
     * @param map es la colección de bolsas con sus reglas.
     * @param color es el color de la bolsa inicial.
     * @return la cantidad total de bolsas.
     */
    public int totalBags(Map<String, Map<String, Integer>> map, String color) {
        AtomicInteger ac = new AtomicInteger(0);
        map.get(color).entrySet().stream().forEach(son -> {
            ac.addAndGet(son.getValue() * totalBags(map, son.getKey()));
            if (map.get(son.getKey()).size() > 0) {
                ac.addAndGet(son.getValue());
            }
        });
        return Math.max(ac.get(), 1);
    }

    /**
     * Crea una nueva colección para contener todas las bolsas y sus reglas.
     *
     * @return una colección vacia.
     */
    @Override
    public Supplier<Map<String, Map<String, Integer>>> supplier() {
        return HashMap::new;
    }

    /**
     * Procesa cada regla y obtiene el color de la bolsa como llave, y como
     * valor una colección con las cantidad de bolsas y su color.
     *
     * @return la colección con todas las bolsas y sus reglas.
     */
    @Override
    public BiConsumer<Map<String, Map<String, Integer>>, String> accumulator() {
        return (map, rule) -> {
            Scanner scan = new Scanner(rule).useDelimiter(", ");
            String color = scan.next();
            Map<String, Integer> colors = new HashMap<>();
            while (scan.hasNext()) {
                String r = scan.next();
                if (!r.equals("no other") && !r.equals("nil")) {
                    String[] at = r.split(" ", 2);
                    colors.put(at[1], Integer.parseInt(at[0]));
                }
            }
            map.put(color, colors);
        };
    }

    /**
     * Combina dos colecciónes de bolsas.
     * 
     * @return una colección con el contenido de ambas colecciones.
     */
    @Override
    public BinaryOperator<Map<String, Map<String, Integer>>> combiner() {
        return (acc, ps) -> {
            acc.putAll(ps);
            return acc;
        };
    }
    
    /**
     * Termina de llenar la colección.
     * 
     * @return la colección con todas las bolsas y sus reglas.
     */
    @Override
    public Function<Map<String, Map<String, Integer>>, Map<String, Map<String, Integer>>> finisher() {
        return map -> map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));
    }

}
