package de.awtools.functionalprogramming;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    void doItWithResult() throws Exception {
        Result<Integer,Exception> attempt = Result.attempt(() -> decode("4711"));
        
        assertThat(attempt.map(x -> x).orElseThrow()).isEqualTo(4711);

        assertThat(Result.attempt(() -> ResultTest.decode("4711")).map(x -> x).orElseThrow()).isEqualTo(4711);
        
        assertThat(Result.attempt(() -> Integer.decode("4711")).map(x -> x).orElseThrow()).isEqualTo(4711);
        
        assertThat(Result.attempt(() -> Integer.decode("4711")).map(x -> x).orElseThrow()).isEqualTo(4711);
        
        assertThatThrownBy(() -> Result.attempt(() -> Integer.decode("x4711")).map(x -> x).orElseThrow()).isInstanceOf(NumberFormatException.class);
    }

    public static Integer decode(String number) throws Exception {
        try {
            return Integer.decode(number);
        } catch (NumberFormatException ex) {
            throw new Exception(ex);
        }
    }

    public static Integer tryGet2(String number) {
        return Integer.decode(number);
    }

    // ----

    /**
     * Get the API's base URL from the environment.
     * 
     * @return The URI, or null if the environment variable is missing.
     */
    URI apiBaseURLFromEnv() throws URISyntaxException {
        // Result<URI,URISyntaxException> attempt = Result.attempt(() -> new URI("https://www.andre-winkler.de"));
        
        Optional<Result<URI, URISyntaxException>> apiBaseURL = Optional.ofNullable(System.getenv("apiBaseURL"))
                .map(urlString -> Result.attempt(() -> new URI(urlString)).map(URI::normalize));

        if (!apiBaseURL.isPresent()) {
            return null;
        }

        return apiBaseURL.get().orElseThrow();
    }

}
