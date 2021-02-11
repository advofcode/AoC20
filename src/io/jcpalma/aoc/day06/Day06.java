/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day06;

import io.jcpalma.aoc.Day;
import java.util.List;

/**
 *
 * @author jcpalma
 */
public class Day06 extends Day {

    @Override
    public void run() {

        System.out.println("--- Day 6: Custom Customs ---");

        List<Group> groups = streamInput().collect(new GroupCollector());

        /////////////////////////////////////////////////////////////////////
        //                          Primera parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part One <<");
        int counts = groups.stream().mapToInt(Group::answeredYes).sum();
        System.out.printf("  My puzzle answer was: %d%n", counts);

        /////////////////////////////////////////////////////////////////////
        //                          Segunda parte                          //
        /////////////////////////////////////////////////////////////////////
        System.out.println(">> Part Two <<");
        long result = groups.stream().mapToLong(Group::allAnsweredYes).sum();
        System.out.printf("  My puzzle answer was: %d%n", result);

    }

}
