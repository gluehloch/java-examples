package optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalAssertions {

    @Test
    void optionalAssertions() {
        assertThat(Optional.of("TEST")).contains("TEST");
        assertThat(Optional.of("TEST")).hasValue("TEST");
    }
    
}
