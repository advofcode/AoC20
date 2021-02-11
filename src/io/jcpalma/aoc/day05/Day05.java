/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day05;

import io.jcpalma.aoc.Day;
import java.util.List;

/**
 *
 * @author jcpalma
 */
public class Day05 extends Day {

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

    public static class Seat {

        private final String pass;

        public Seat(String pass) {
            this.pass = pass;
        }

        public int getSeatId() {
            return getRow() * 8 + getCol();
        }

        private int getRow() {
            return encode(pass.substring(0, 7), 0, 127, 'F', 'B');
        }

        private int getCol() {
            return encode(pass.substring(7), 0, 7, 'L', 'R');
        }

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
