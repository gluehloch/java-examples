package optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class OptionalShowCaseTest {

    @Test
    void optionalReturnNull() {
        assertThat(calculate("Andre").map(a -> "Winkler").orElse("empty")).isEqualTo("Winkler");
        assertThat(calculate("Lars").map(a -> "Winkler").orElse("empty")).isEqualTo("empty");

        assertThat(calculate("Andre").map(a -> "Winkler").orElse(null)).isEqualTo("Winkler");
        assertThat(calculate("Lars").map(a -> "Winkler").orElse(null)).isNull();
    }

    @Test
    void streamingOptional() {
        Optional<String> emptyString = Optional.empty();
        Optional<String> someString = Optional.of("Not Empty");

        Optional<Boolean> empty = Optional.empty();

        assertThat(emptyString.stream().map(i -> i.startsWith("Empty")).collect(Collectors.toSet())).isEmpty();
        assertThat(someString.stream().map(i -> i.endsWith("Empty")).collect(Collectors.toSet()))
                .containsExactlyInAnyOrder(Boolean.TRUE);

        assertThat(empty).isEmpty();
        empty.stream().map(i -> i.booleanValue());
    }

    @Test
    void optionalMapOrElse() {
        assertThat(Optional.of("AWTools").map(String::toUpperCase).orElse("empty")).isEqualTo("AWTOOLS");
        assertThat(Optional.<String>empty().map(String::toUpperCase).orElse("empty")).isEqualTo("empty");
    }

    private Optional<String> calculate(String string) {
        if (string.startsWith("A")) {
            return Optional.of("Andre");
        } else {
            return Optional.empty();
        }
    }

    @Test
    void ifPresentThrowExceptionOrElse() {
        String string = "Andre";
        Optional.of(string).ifPresentOrElse(
                (a) -> new IllegalArgumentException(string + " is present"), () -> {
                    var a = 0;
                });
    }

    @Test
    void castOptional() {
        Optional<Entity> entity = Optional.of(new Entity());
        // Optional<Identifiable> identifiable = entity; // Compile error
        Optional<Identifiable> identifiable = entity.map(e -> (Identifiable) e); // OK
        assertThat(identifiable).isPresent();
    }

    private static interface Identifiable {
        Long getId();
    }

    private static class Entity implements Identifiable {
        public Long getId() {
            return Random.from(RandomGenerator.getDefault()).nextLong();
        }
    }
}
