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

/**
 * Representa un campo del pasaporte.
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.4
 */
public class Field {

    private final boolean required;
    private final boolean valid;
    private final String key;
    private final String value;

    /**
     * Crea una instancia de un campo.
     *
     * @param key es el nombre del campo.
     * @param value es el valor del campo.
     * @param required indica si es requerido.
     */
    public Field(String key, String value, boolean required) {
        this.key = key;
        this.required = required;
        this.value = value;
        this.valid = checkRules();
    }

    /**
     * Devuelve el nombre del campo.
     *
     * @return el nombre del campo.
     */
    public String key() {
        return key;
    }

    /**
     * Devuelve el valor del campo.
     *
     * @return el valor del campo.
     */
    public String value() {
        return this.value;
    }

    /**
     * Indica si es requerido.
     *
     * @return true si es requerido, sino false.
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Indica si el valor del campo es válido.
     *
     * @return true si el valor del campo es válido.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Indica si el valor del campo no es válido.
     *
     * @return true si el valor del campo no es válido.
     */
    public boolean isNotValid() {
        return !valid;
    }

    @Override
    public String toString() {
        return String.format("{%s%s = %s}", key, required ? "*" : " ", value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Field)) {
            return false;
        }

        Field other = (Field) obj;
        return key.equals(other.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    /**
     * Realiza la validación del valor del campo según las reglas establecidas.
     *
     * @return true si el valor es válido.
     */
    private boolean checkRules() {
        switch (key) {
            case "byr":
                return byrIsValid();
            case "iyr":
                return iyrIsValid();
            case "eyr":
                return eyrIsValid();
            case "hgt":
                return hgtIsValid();
            case "hcl":
                return value.matches("^[#][0-9a-f]{6}$");
            case "ecl":
                return value.matches("^(amb|blu|brn|gry|grn|hzl|oth)$");
            case "pid":
                return value.matches("^[0-9]{9}$");
            default:
                return true;
        }
    }

    /**
     * Válida que el año de nacimiento esté entre 1920 y 2002.
     *
     * @return true si está entre 1920 y 2002.
     */
    private boolean byrIsValid() {
        int year = 0;
        try {
            year = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return year >= 1920 && year <= 2002;
    }

    /**
     * Válida que el año de emisión esté entre 2010 y 2020.
     *
     * @return true si está entre 2010 y 2020.
     */
    private boolean iyrIsValid() {
        int year = 0;
        try {
            year = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return year >= 2010 && year <= 2020;
    }

    /**
     * Válida que el año de expiración esté entre 2020 y 2030.
     *
     * @return true si está entre 2020 y 2030.
     */
    private boolean eyrIsValid() {
        int year = 0;
        try {
            year = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return year >= 2020 && year <= 2030;
    }

    /**
     * Válida que el peso lleve la únidad de medida (cm, in). En caso que sean
     * cm el peso debe estar entre 150 y 193, si está en pulgadas (in) el peso
     * debe estar entre 59 y 76.
     *
     * @return true si el pesa está entre los valores requeridos.
     */
    private boolean hgtIsValid() {
        int height = 0;
        if (!value.matches("^[0-9]+(cm|in)$")) {
            return false;
        }

        try {
            height = Integer.parseInt(value.replaceFirst("cm|in", ""));
        } catch (RuntimeException ex) {
            return false;
        }

        return value.endsWith("cm") ? (height >= 150 && height <= 193) : (height >= 59 && height <= 76);
    }

}
