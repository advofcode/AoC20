/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day03;

import io.jcpalma.aoc.Day;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author jcpalma
 */
public class Day03 extends Day {

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
