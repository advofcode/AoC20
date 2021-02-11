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

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa un grupo de personas con las lista de preguntas que respondieron
 * que 'si'.
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.6
 */
public class Group {

    private static AtomicInteger key = new AtomicInteger(1);

    private final int id;
    private final Map<Character, Integer> questions;
    private int people = 0;

    /**
     * Crea una instancia de Group sin respuestas.
     */
    public Group() {
        this.id = key.getAndIncrement();
        this.questions = new Hashtable<>();
    }

    /**
     * Agregar las respuestas de una persona.
     *
     * @param answers son las preguntas respondidas afirmativamente.
     */
    public void add(String answers) {
        answers.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> {
                    Integer x = questions.getOrDefault(c, 0) + 1;
                    questions.put(c, x);
                });
        people++;
    }

    /**
     * Devuelve la cantidad de preguntas distintas que fueron respondidas.
     *
     * @return cantidad de preguntas distintas.
     */
    public int answeredYes() {
        return questions.size();
    }

    /**
     * Devuelve la cantidad de preguntas distintas que fueron respondidas por
     * todas las personas del grupo.
     *
     * @return la cantidad de preguntas respondidas por todos.
     */
    public long allAnsweredYes() {
        return questions.values().stream().filter(x -> x == people).count();
    }

    @Override
    public String toString() {
        return String.format("Group: %d%n  peoples: %d%n  questions: %d%n", id, people, answeredYes());
    }

}
