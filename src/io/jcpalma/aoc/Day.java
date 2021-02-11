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
package io.jcpalma.aoc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * La super clase día, que encapsula la lógica para cargar el archivo de entrada
 * como el archivo de prueba. Por defecto el archivo de prueba debe estar dentro
 * del paquete donde está la soluciós del día, con el nombre 'input.dat', y para
 * el de prueba es 'test.dat'.
 *
 * @author José Carlos Palma {@literal <palmahn@gmail.com>}
 * @version v2020.0
 */
public abstract class Day implements Runnable {

    private final Path input;
    private final Path test;

    /**
     * Inicializa los archivos temporales donde están las entredas.
     */
    public Day() {
        this.input = Path.of(".$input.dat");
        this.test = Path.of(".$test.dat");
    }

    /**
     * Devuelve la ruta del archivo según la clase del día.
     *
     * @return Una cadena con la ruta relativa del archivo.
     */
    private String getRelativePath() {
        return getClass().getName().replaceFirst("[.][a-zA-Z0-9]+$", "").replaceAll("[.]", File.separator);
    }

    /**
     * Devuelve un stream con el contenido del archivo.
     *
     * @param path donde se copia el archivo original.
     * @param name nombre del archivo original.
     * @return un stream de String por cada línea del archivo.
     */
    private Stream<String> stream(Path path, String name) {

        String fileName = "/" + getRelativePath() + "/" + name;
        try {

            InputStream in = getClass().getResourceAsStream(fileName);

            if (Objects.isNull(in)) {
                System.out.printf("WARNING: %s no existe.%n", fileName);
                return Stream.empty();
            }
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            path.toFile().deleteOnExit();
            return Files.lines(path);
        } catch (IOException | NullPointerException ex) {
            return Stream.empty();
        }
    }

    /**
     * Devuelve un stream con el contenido del archivo 'input.dat'.
     *
     * @return un stream de String por cada línea del archivo.
     */
    protected final Stream<String> streamInput() {
        return stream(input, "input.dat");
    }

    /**
     * Devuelve una lista con el contenido del archivo 'input.dat'.
     *
     * @return una lista de String por cada línea del archivo.
     */
    protected final List<String> inputToList() {
        return streamInput().collect(Collectors.toList());
    }

    /**
     * Devuelve una lista con el contenido del archivo de prueba 'test.dat'.
     *
     * @return una lista de String por cada línea del archivo de prueba.
     */
    protected final Stream<String> streamTest() {
        return stream(test, "test.dat");
    }

    /**
     * Devuelve una lista con el contenido del archivo 'input.dat'.
     *
     * @return una lista de String por cada línea del archivo.
     */
    protected final List<String> testToList() {
        return streamTest().collect(Collectors.toList());
    }

    /**
     * Ejecuta la solución para el día específico.
     */
    @Override
    public abstract void run();

}
