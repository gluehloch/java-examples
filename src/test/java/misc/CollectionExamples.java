package misc;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	public void sortCollections2() {
	    var list = new ArrayList<String>();
	    list.add("1234567");
	    list.add("12345");
	    list.add("123");
	    list.add("12");
	    list.add("1");

	    // Collections.sort
	    Collections.sort(list, Comparator.comparingInt(String::length));	    
	    assertThat(list.get(0)).isEqualTo("1");
	    assertThat(list.get(1)).isEqualTo("12");
	    assertThat(list.get(2)).isEqualTo("123");
	    assertThat(list.get(3)).isEqualTo("12345");
	    assertThat(list.get(4)).isEqualTo("1234567");
	    
	    // List.sort
	    list.sort(Comparator.comparingInt(String::length));
        assertThat(list.get(0)).isEqualTo("1");
        assertThat(list.get(1)).isEqualTo("12");
        assertThat(list.get(2)).isEqualTo("123");
        assertThat(list.get(3)).isEqualTo("12345");
        assertThat(list.get(4)).isEqualTo("1234567");
        
        // TODO Das ist ein interessanted Konstrukt. Kann man das irgendwie sinnvoll verwenden?
        assertThat(list).extracting(e -> e.charAt(0), e -> e.toLowerCase()).contains(tuple('1', "1"));
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
