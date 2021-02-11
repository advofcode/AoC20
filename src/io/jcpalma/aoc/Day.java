/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author jcpalma
 */
public abstract class Day implements Runnable {
    
    private final Path input;
    private final Path test;
    
    public Day() {
        this.input = Path.of(".$input.dat");
        this.test = Path.of(".$test.dat");
    }
    
    private String getRelativePath() {
        return getClass().getName().replaceFirst("[.][a-zA-Z0-9]+$", "").replaceAll("[.]", File.separator);
    }
    
    private Stream<String> stream(Path path, String name) {
        
        String fileName = "/" + getRelativePath() + "/" + name;
        try {
            
            InputStream in = getClass().getResourceAsStream(fileName);
            
            if( Objects.isNull(in) ) {
                System.out.printf("WARNING: %s no existe.%n", fileName);
                return Stream.empty();
            }
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            path.toFile().deleteOnExit();
            return Files.lines(path);
        } catch (IOException| NullPointerException ex) {
            return Stream.empty();
        }
    }
    
    protected final Stream<String> streamInput() {
        return stream(input, "input.dat");
    }
    
    protected final List<String> inputToList(){
        return streamInput().collect(Collectors.toList());
    }
    
    protected final Stream<String> streamTest() {
        return stream(test, "test.dat");
    }
    
    protected final List<String> testToList(){
        return streamTest().collect(Collectors.toList());
    }
    
    

    
    @Override
    public abstract void run();

    
    
}
