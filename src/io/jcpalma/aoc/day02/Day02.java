/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day02;

import io.jcpalma.aoc.Day;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author jcpalma
 */
public class Day02 extends Day {

    @Override
    public void run() {

        System.out.println("--- Day 2: Password Philosophy ---");

        List<String> pass = inputToList();

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        long result = pass.stream().flatMap(p -> Stream.of(new Password(p)))
                .filter(Password::accept)
                .count();
        System.out.println("  My puzzle answer was: " + result);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        result = pass.stream().flatMap(p -> Stream.of(new Password(p)))
                .filter(Password::acceptPolicy)
                .count();
        System.out.println("  My puzzle answer was: " + result);

    }

    public static class Password {

        private final String times;
        private final String letter;
        private final String pass;

        public Password(String line) {
            String[] data = line.split("([ ]|[:])", 3);
            this.times = data[0];
            this.letter = data[1];
            this.pass = data[2].trim();
        }

        public boolean accept() {
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

        public boolean acceptPolicy() {
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
