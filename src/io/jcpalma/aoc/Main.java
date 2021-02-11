/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author jcpalma
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Welcome to 'Advent of Code'");

        final DecimalFormat df = new DecimalFormat("00");
        final Scanner read = new Scanner(System.in);

        do {
            System.out.println("--- Choose a day (type 'q' to quit) ---");
            System.out.print("Day: ");
            String cin = read.nextLine().strip();

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
                } catch (Exception ex) { }
            }

            if (cin.toLowerCase().matches("q|quit|exit")) {
                break;
            }

        } while (true);

    }

}
