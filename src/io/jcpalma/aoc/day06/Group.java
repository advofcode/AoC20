/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day06;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author jcpalma
 */
public class Group {
    
    private static AtomicInteger key = new AtomicInteger(1);
    
    private final int id;
    private final Map<Character,Integer> questions;
    private int people = 0;
    
    public Group() { 
        this.id = key.getAndIncrement();
        this.questions = new Hashtable<>();
    }
    
    public void add(String answers) {
        answers.chars()
                .mapToObj(c -> (char)c)
                .forEach( c -> {
                    Integer x = questions.getOrDefault(c, 0) + 1;
                    questions.put(c, x);
                });
        people++;
    }
    
    public int answeredYes() {
        return questions.size();
    }
    
    public long allAnsweredYes() {
        return questions.values().stream().filter(x -> x == people).count();
    }

    @Override
    public String toString() {
        return String.format("Group: %d%n  peoples: %d%n  questions: %d%n", id, people, answeredYes());
    }
    
    
}
