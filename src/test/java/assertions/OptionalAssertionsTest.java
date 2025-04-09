package assertions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalAssertionsTest {

    @Test
    void validateOptional() {
        var optional = Optional.of("Andre Winkler");
        assertThat(optional)
                .isPresent()
                .hasValueSatisfying(o -> {
                    assertThat(o).isEqualTo("Andre Winkler");
                });
    }

}
