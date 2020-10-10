package misc;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Read a test file from the classpath
 * 
 * @author Andre Winkler
 * @since 2020
 */
public class ReadTestFileFromClasspath {

    @Test
    public void readFileFromClasspath() {
        InputStream inputStream = ReadTestFileFromClasspath.class.getResourceAsStream("TestFile.txt");
        assertThat(new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n")))
                .isEqualTo("This is a test.");
    }

}
