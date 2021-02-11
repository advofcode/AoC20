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
 * Se encarga de generar una lista de pasaportes con sus respectivos compos y
 * valores. Cada pasaporte está separado por una línea en blanco en el archivo.
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.4
 */
public class PassportsCollector implements Collector<String, List<Passport>, List<Passport>> {

    private Passport passport = new Passport();

    /**
     * Devuelve una lista vacia de pasaportes.
     *
     * @return una lista de pasaportes vacia.
     */
    @Override
    public Supplier<List<Passport>> supplier() {
        return ArrayList::new;
    }

    /**
     * Inserta a la lista un pasaporte cuando encuentra una línea en blanco.
     * Sí no es una línea en blanco significa que son campos del pasaporte.
     *
     * @return una función lambda con dicha operación.
     */
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

    /**
     * Combina dos lista de pasaportes.
     *
     * @return la lista unificada.
     */
    @Override
    public BinaryOperator<List<Passport>> combiner() {
        return (acc, ps) -> {
            acc.addAll(ps);
            return acc;
        };
    }

    /**
     * Termina de procesar el archivo y agrega el último pasaporte.
     *
     * @return una lista con todos los pasaportes.
     */
    @Override
    public Function<List<Passport>, List<Passport>> finisher() {
        return list -> {
            list.add(passport);
            return list;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT));
    }
}
