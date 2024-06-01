package de.awtools.jdk;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;

/**
 * JavaSpecialist [318] Getting Rid of Unused Warnings with _
 */
public class IncorrectWarningDemo {

    public static void main(String... args) {
        try (FileChannel fc = FileChannel.open(Path.of("bla"));
            //@SuppressWarnings("unused") // shouldn't need this
            // SuppressWarnings does not turn of -Xlint warning
            FileLock flck = fc.lock()) { // or tryLock() ...
            try (FileLock fileLock = fc.lock()) {
                fc.size();
            }
            // use try-with-resources to lock some file i/O ...
            // flck is not referenced in the try/catch/finally
            fc.write(ByteBuffer.allocate(10));
        } catch (IOException ex) {
            // ...
        }
    }

}