package optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class OptionalShowCase {

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
			(a) -> new IllegalArgumentException(string + " is present")
			,  () -> { var a = 0;}
		);
	}

}
