/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 *
 * @author jcpalma
 */
public class GroupCollector implements Collector<String, List<Group>, List<Group>> {
    
    private Group group = new Group();

    @Override
    public Supplier<List<Group>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Group>, String> accumulator() {
        return ( groups, answers ) -> {
            if( answers.isEmpty() ) {
                groups.add(group);
                group = new Group();
            }else {
                group.add(answers);
            }
            
        };
    }

    @Override
    public BinaryOperator<List<Group>> combiner() {
        return (acc, ps) -> {
            acc.addAll(ps);
            return acc;
        };
    }

    @Override
    public Function<List<Group>, List<Group>> finisher() {
        return list -> {
            list.add(group);
            group = new Group();
            return list;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));
    }
    
}
