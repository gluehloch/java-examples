package misc;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Some new Java 9 / 10 features.
 * 
 * @author Andre Winkler
 * @since 2018
 */
public class CollectionExamples {

	@Test
	public void createCollections() {
		var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println(integers2);

		var integers3 = Set.of(1, 2, 3, 5, 6, 7, 8);
		System.out.println(integers3);

		// Map
		Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
		var map2 = Map.of("a", 1, "b", 2, "c", 3);
		System.out.println(map);
		System.out.println(map2);

		Map<Integer, String> map3 = Map.ofEntries(entry(1, "Andre"), entry(2, "Lars"));
		System.out.println(map3);
	}

	@Test
	public void sortCollections() {
		var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

		Assertions.assertThrows(UnsupportedOperationException.class, () -> integers2.sort((a, b) -> a.compareTo(b)));
	}

	@Test
	public void toMap() {
		var integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Map<Integer, Integer> map = integers.stream().collect(Collectors.toMap(i -> i, j -> j * 2));
		assertThat(map).hasSize(10);

		for (int i = 1; i <= 10; i++) {
			assertThat(map.get(i)).isEqualTo(i * 2);
		}
	}

	/**
	 * Vergleiche mit {@link #sortCollections()}
	 */
	@Test
	public void sortCollectionsAndAssertJ() {
		var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

		assertThatExceptionOfType(UnsupportedOperationException.class)
				.isThrownBy(() -> integers2.sort((a, b) -> a.compareTo(b)));

//		assertThatExceptionOfType(UnsupportedOperationException.class)
//				.isThrownBy(() -> integers2.sort((a, b) -> a.compareTo(b)))
//				.withMessage("%s", "kaputt");
	}

}
