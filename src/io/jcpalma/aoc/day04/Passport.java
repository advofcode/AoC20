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
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Representa un pasaporte que contiene una lista de campos.
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.4
 */
public class Passport {

    private static final Set<String> REQUIRED_FIELDS = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    private final List<Field> fields = new ArrayList<>();

    /**
     * Crea una instancia de pasaporte sin ningún campo.
     */
    public Passport() {
    }

    /**
     * Agrega los campos al pasaporte.
     *
     * @param data es una cadena que contiene uno o más campos con su respectivo
     * valor separado cada conjunto por espacios en blancos.
     */
    public void addFields(String data) {
        Scanner s = new Scanner(data).useDelimiter("[ ]+");
        while (s.hasNext()) {
            String[] at = s.next().strip().split(":", 2);
            String key = at[0];
            String value = at[1];
            boolean required = Passport.isFieldRequired(key);
            fields.add(new Field(key, value, required));
        }
    }

    /**
     * Indica si el pasaporte tiene todos los campos requeridos.
     *
     * @return true si tiene todos los campos requeridos.
     */
    public boolean hasAllRequiredFields() {
        return fields.stream().filter(Field::isRequired).count() == REQUIRED_FIELDS.size();
    }

    /**
     * Indica si los campos requeridos que están en el pasaporte, su valor es
     * válido.
     *
     * @return true si los valores son válidos para los campos requeridos.
     */
    public boolean hasAllDataRulesPass() {
        boolean hasInvalid = fields.stream()
                .filter(Field::isRequired)
                .anyMatch(Field::isNotValid);
        return !hasInvalid;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Fields: ")
                .append(fields.size())
                .append("\n")
                .append(fields)
                .toString();
    }

    /**
     * Indica si un campo es requerido.
     *
     * @param key es el nombre del campo.
     * @return true si el campo es requerido.
     */
    private static boolean isFieldRequired(String key) {
        return REQUIRED_FIELDS.contains(key);
    }
}
