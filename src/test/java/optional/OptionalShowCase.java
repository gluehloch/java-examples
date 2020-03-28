package optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class OptionalShowCase {

    @Test
    public void optionalReturnNull() {
        assertThat(calculate("Andre").map((a) -> "Winkler").orElse(null)).isEqualTo("Winkler");
        assertThat(calculate("Lars").map((a) -> "Winkler").orElse(null)).isNull();
    }
    
    private Optional<String> calculate(String string) {
        if (string.startsWith("A")) {
            return Optional.of("Andre");
        } else {
            return Optional.empty();
        }
    }

}
