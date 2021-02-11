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
 * Se encarga de generar la lista de grupos de personas con sus preguntas
 * respondidas.
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.6
 */
public class GroupCollector implements Collector<String, List<Group>, List<Group>> {

    private Group group = new Group();

    /**
     * Devuelve una lista vacia de grupos de personas.
     *
     * @return una lista de grupos vacia.
     */
    @Override
    public Supplier<List<Group>> supplier() {
        return ArrayList::new;
    }

    /**
     * Inserta a la lista un grupo cuando encuentra una línea en blanco. Sí no
     * es una línea en blanco significa que son las preguntas de una persona del
     * grupo actual.
     *
     * @return una función lambda con dicha operación.
     */
    @Override
    public BiConsumer<List<Group>, String> accumulator() {
        return (groups, answers) -> {
            if (answers.isEmpty()) {
                groups.add(group);
                group = new Group();
            } else {
                group.add(answers);
            }

        };
    }

    /**
     * Combina dos lista de grupos de personas con sus preguntas respondidas.
     *
     * @return la lista unificada.
     */
    @Override
    public BinaryOperator<List<Group>> combiner() {
        return (acc, ps) -> {
            acc.addAll(ps);
            return acc;
        };
    }

    /**
     * Termina de procesar el archivo y agrega el último grupo.
     *
     * @return una lista con todos los grupos.
     */
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
