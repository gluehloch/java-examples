package de.awtools.functionalprogramming;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

import de.awtools.functionalprogramming.Result.CheckedSupplier;

public class ResultDemo {
    
    public void start() {
        Result<File, Exception> result = Result.attempt(new MyCheckedSupplier());
        result.map(file -> {
            System.out.println("Created temporary file.");
            return "OK";
        });
    }

    public void checkedSupplier() {

    }

    private static class MyCheckedSupplier implements CheckedSupplier<File, Exception> {
        @Override
        public File get() throws IOException {
            String tmpdir = System.getProperty("java.io.tmpdir");
            System.out.println("Temp file path: " + tmpdir);

            Path temp = Files.createTempFile("awtools", ".txt");
            System.out.println("Temp file : " + temp);

            return temp.toFile();
        }
    }

}
