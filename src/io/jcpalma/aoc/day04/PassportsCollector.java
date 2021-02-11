/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jcpalma.aoc.day04;

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
public class PassportsCollector implements Collector<String, List<Passport>, List<Passport>> {

    private Passport passport = new Passport();

    @Override
    public Supplier<List<Passport>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Passport>, String> accumulator() {
        return (list, d) -> {
            if (d.isEmpty()) {
                list.add(passport);
                passport = new Passport();
            } else {
                passport.addFields(d);
            }
        };
    }

    @Override
    public BinaryOperator<List<Passport>> combiner() {
        return (acc, ps) -> {
            acc.addAll(ps);
            return acc;
        };
    }

    @Override
    public Function<List<Passport>, List<Passport>> finisher() {
        return list -> list;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));
    }
}
