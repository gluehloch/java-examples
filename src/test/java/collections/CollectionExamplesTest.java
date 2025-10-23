package collections;

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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Some new Java 9 / 10 features.
 * 
 * @author Andre Winkler
 * @since 2018/2021
 */
class CollectionExamplesTest {

    public void joinStrings() {
        List<String> things = List.of("A", "B", "C", "D", "E");

        // Accumulate names into a List
        List<String> list = things.stream().map(Object::toString).collect(Collectors.toList());
        assertThat(list).containsExactly("A", "B", "C", "D", "E");

        // Convert elements to strings and concatenate them, separated by commas
        String joined = things.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertThat(joined).isEqualTo("A, B, C, D, E");
    }

    @Tag("collections")
    @Test
    void createCollections() {
        // List
        var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(integers2);
        assertThat(integers2).contains(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Set
        var integers3 = Set.of(1, 2, 3, 5, 6, 7, 8);
        System.out.println(integers3);
        assertThat(integers3).contains(1, 2, 3, 5, 6, 7, 8);

        // Map
        Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
        var map2 = Map.of("a", 1, "b", 2, "c", 3);
        System.out.println(map);
        System.out.println(map2);
        assertThat(map).containsAllEntriesOf(map2);

        Map<Integer, String> map3 = Map.ofEntries(entry(1, "Andre"), entry(2, "Lars"));
        System.out.println(map3);
    }

    @Tag("collections")
    @Test
    void sortCollections2() {
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

        /* Siehe auch {@code ListAssert#assertJdemo} Demo. */
        assertThat(list).extracting(e -> e.charAt(0), e -> e.toLowerCase()).contains(tuple('1', "1"));
    }

    @Tag("collections")
    @Test
    void sortCollections() {
        var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> integers2.sort((a, b) -> a.compareTo(b)));
    }

    @Tag("collections")
    @Test
    void toMap() {
        var integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Integer, Integer> map = integers.stream().collect(Collectors.toMap(i -> i, j -> j * 2));
        assertThat(map).hasSize(10);

        for (int i = 1; i <= 10; i++) {
            assertThat(map.get(i)).isEqualTo(i * 2);
        }

        assertThat(map).containsExactly(
                entry(1, 2),
                entry(2, 4),
                entry(3, 6),
                entry(4, 8),
                entry(5, 10),
                entry(6, 12),
                entry(7, 14),
                entry(8, 16),
                entry(9, 18),
                entry(10, 20));
    }

    /**
     * Vergleiche mit {@link #sortCollections()}
     */
    @Tag("collections")
    @Test
    void sortCollectionsAndAssertJ() {
        var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> integers2.sort((a, b) -> a.compareTo(b)));

        // assertThatExceptionOfType(UnsupportedOperationException.class)
        // .isThrownBy(() -> integers2.sort((a, b) -> a.compareTo(b)))
        // .withMessage("%s", "kaputt");
    }

    @Tag("collections")
    @Test
    void findAllElementsWhichArePartOfAnotherCollection() {
        var integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertThat(integers).containsAll(integers2);

        var integers3 = integers.stream().filter(integers2::contains).toList();
        assertThat(integers2).containsExactlyElementsOf(integers3);
    }

    @Tag("collections")
    @Test
    void removeAllElementsWhichArePartOfAnotherCollection() {
        var integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        integers.removeAll(integers2);
        assertThat(integers).containsExactly(10);
    }

    @Tag("collections")
    @Test
    void removeAllElementsWhichArePartOfAnotherCollection2() {
        var persons = new ArrayList<>(List.of(
                new Person("Andre", 54),
                new Person("Adam", 19),
                new Person("Lars", 17),
                new Person("Erwin", 14)));
        var personsToRemove = List.of("Adam", "Lars", "Erwin");

        // remove all persons whose name is contained in personsToRemove
        persons.removeIf(p -> personsToRemove.contains(p.name()));

        assertThat(persons).hasSize(1);
        assertThat(persons.get(0).name()).isEqualTo("Andre");
    }

    @Tag("collections")
    @Test
    void removeAllElementsWhichArePartOfAnotherCollection3() {
        var persons = List.of("Adam", "Lars", "Erwin");
        var personsToRemove = new ArrayList<>(List.of(
                new Person("Andre", 54),
                new Person("Adam", 19),
                new Person("Lars", 17),
                new Person("Erwin", 14)));

        // remove all strings from 'persons' if a Person with the same name exists in personsToRemove
        var remaining = new ArrayList<>(persons);
        remaining.removeIf(name -> personsToRemove.stream().anyMatch(p -> p.name().equals(name)));

        assertThat(remaining).isEmpty();
    }

    private static record Person(String name, int age) {
    }

}
