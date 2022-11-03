package optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.STRING;

import java.util.Optional;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class OptionalAssertionsTest {

    private final TolkienCharacter legolas = new TolkienCharacter(Race.ELF, "Legolas");

    @Test
    void optionalAssertions() {
        assertThat(Optional.of("TEST")).contains("TEST");
        assertThat(Optional.of("TEST")).hasValue("TEST");

        assertThat(Optional.of("TEST")).isPresent()
                .containsInstanceOf(String.class)
                .contains("Test");

        String someString = "something";
        assertThat(Optional.of(someString)).containsSame(someString);
        assertThat(Optional.of(someString)).hasValueSatisfying(s -> {
            assertThat(s).isEqualTo("something");
            assertThat(s).startsWith("some");
            assertThat(s).endsWith("thing");
        });

        Condition<TolkienCharacter> isAnElf = new Condition<>(character -> character.getRace() == Race.ELF, "an elf");

        assertThat(Optional.of(legolas)).hasValueSatisfying(isAnElf);
        assertThat(Optional.of(legolas)).satisfiesAnyOf(tc -> then(tc).isEmpty(),
                tc -> then(tc.get().getRace()).isEqualTo(Race.ELF));
        assertThat(Optional.empty()).satisfiesAnyOf(tc -> then(tc).isEmpty(), tc -> then(true).isFalse());
        
        assertThat(Optional.of(legolas)).get().isNotNull();

        Optional<String> optional = Optional.of("Frodo");

        assertThat(optional).get(as(STRING)).startsWith("Fro");        
    }

    private enum Race {
        HUMANE, ELF, ORK, GOBLIN, HOBBIT
    }

    private static class TolkienCharacter {
        private final Race race;
        private final String name;

        TolkienCharacter(Race race, String name) {
            this.race = race;
            this.name = name;
        }

        public Race getRace() {
            return race;
        }
        
        public String getName() {
            return name;
        }

    }
}
