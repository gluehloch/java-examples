package streams;

import static java.util.function.Function.identity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class StreamExamplesTest {

    public static final String[] WINKLERS = { "Andre", "Christine", "Adam", "Lars", "Erwin" };

    @DisplayName("Example: Filter stream of optionals.")
    @Tag("streams")
    @Test
    void filterOptionalStream() {
        List<Optional<String>> listOfOptionals = List.of(
                Optional.of("Andre"),
                Optional.of("Christine"),
                Optional.of("Adam"),
                Optional.of("Lars"),
                Optional.empty(),
                Optional.of("Erwin"));
        List<String> filteredList = listOfOptionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        assertThat(filteredList).containsExactly(WINKLERS);

        List<String> filteredList2 = listOfOptionals.stream()
                .flatMap(o -> o.stream().flatMap(Stream::of))
                .toList();
        assertThat(filteredList2).containsExactly(WINKLERS);

        List<String> filteredList21 = listOfOptionals.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .toList();
        assertThat(filteredList21).containsExactly(WINKLERS);

        List<String> filteredList3 = listOfOptionals.stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .toList();
        assertThat(filteredList3).containsExactly(WINKLERS);

        // Java 9
        List<String> filteredList4 = listOfOptionals.stream()
                .flatMap(Optional::stream)
                .toList();
        assertThat(filteredList4).containsExactly(WINKLERS);
    }

    @DisplayName("Example: Optional map vs flatMap")
    @Tag("streams")
    @Test
    void flatMap() {
        Optional<String> name = Optional.of("Andre").map(String::toUpperCase);
        assertThat(Optional.of("Andre").map(String::toUpperCase).orElse("")).isEqualTo("ANDRE");
        String name2 = Optional.of("Andre").flatMap(s -> Optional.of(s.toUpperCase())).orElse("");
        // String name2 = Optional.of("Andre").flatMap(String::toUpperCase);
    }

    @DisplayName("Example: Stream")
    @Tag("streams")
    @Test
    void flatMapOfStream() {
        List<String> list1 = List.of("Andre", "Lars");
        List<String> list2 = List.of("Adam", "Erwin");
        List<String> list3 = List.of("Christine");
        List<String> list4 = List.of("Ernie", "Bert");
        List<List<String>> list = List.of(list1, list2, list3, list4);
        assertThat(list.stream().flatMap(Collection::stream).toList())
                .containsExactlyInAnyOrder("Andre", "Christine", "Adam", "Lars", "Erwin", "Ernie", "Bert");
    }

    @DisplayName("Example: Stream to list")
    @Tag("streams")
    @Test
    void streamToList() {
        List<String> list = List.of("Andre", "Lars", "Adam", "Erwin", "Christine");
        String[] array = list.toArray(new String[0]);

        /* Since JDK 16 */
        List<String> list2 = list.stream().toList();
        assertThat(list2).containsExactly(array);

        /* Between JDK 8 - 15 */
        List<String> list3 = list.stream().collect(Collectors.toList());
        assertThat(list3).containsExactly(array);
        // ... geht natürlich auch so ...
        List<String> list4 = new ArrayList<>(list);
        assertThat(list4).containsExactly(array);
    }

    @DisplayName("Example: Stream to Map")
    @Tag("streams")
    @Test
    void streamToMap() {
        List<String> list = List.of("Andre", "Lars", "Adam", "Erwin", "Christine");
        Map<String, String> map = list.stream().collect(Collectors.toMap(i -> i, identity()));
        assertThat(map.keySet()).containsExactlyInAnyOrder(list.toArray(new String[0]));
    }

    @DisplayName("Example: Stream with #map vs #flatMap")
    @Tag("streams")
    @Test
    void streamToMapVsFlatMap() {
        List<String> list = List.of("Andre", "Lars", "Adam", "Erwin", "Christine");

        List<String> list2 = list.stream().map(String::toLowerCase).toList();
        assertThat(list2).containsExactly("andre", "lars", "adam", "erwin", "christine");

        List<List<String>> listOfLists = List.of(List.of("Andre", "Christine"), List.of("Adam", "Lars", "Erwin"));
        List<String> list3 = listOfLists.stream().flatMap(Collection::stream).map(String::toLowerCase).toList();
        assertThat(list3).containsExactlyInAnyOrder("andre", "lars", "adam", "erwin", "christine");
    }

    @DisplayName("Example: Stream grouping to map")
    @Tag("streams")
    @Test
    void streamGroupingToMap() {
        List<Pair> pairs = List.of(
                Pair.of("a", "1"),
                Pair.of("a", "2"),
                Pair.of("b", "3"),
                Pair.of("b", "4"));

        Map<String, List<Pair>> x = pairs.stream().collect(Collectors.groupingBy(Pair::getKey));
        assertThat(x).hasSize(2);
        assertThat(x.get("a")).hasSize(2);
        assertThat(x.get("b")).hasSize(2);
        assertThat(x.get("a").get(0).getValue()).isEqualTo("1");
        assertThat(x.get("a").get(1).getValue()).isEqualTo("2");
        assertThat(x.get("b").get(0).getValue()).isEqualTo("3");
        assertThat(x.get("b").get(1).getValue()).isEqualTo("4");
    }

    private static class Pair {
        private final String key;
        private final String value;

        public static Pair of(String key, String value) {
            return new Pair(key, value);
        }

        private Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    @DisplayName("Example: Stream grouping to map by length")
    @Tag("streams")
    @Test
    void streamGroupingByLength() {
        List<String> list = List.of("Andre", "Lars", "Adam", "Erwin", "Christine");

        Map<Integer, List<String>> groupedByLength = list.stream().collect(Collectors.groupingBy(String::length));

        assertThat(groupedByLength.get(4)).containsExactlyInAnyOrder("Lars", "Adam");
        assertThat(groupedByLength.get(5)).containsExactlyInAnyOrder("Andre", "Erwin");
        assertThat(groupedByLength.get(9)).containsExactly("Christine");
    }

    @DisplayName("Example: Parallel stream")
    @Tag("streams")
    @Test
    void parallelStream() {
        List<String> list = List.of("Andre", "Lars", "Adam", "Erwin", "Christine");

        Map<Integer, List<String>> groupedByLength = list.parallelStream()
                .collect(Collectors.groupingBy(String::length));

        assertThat(groupedByLength.get(4)).containsExactlyInAnyOrder("Lars", "Adam");
        assertThat(groupedByLength.get(5)).containsExactlyInAnyOrder("Andre", "Erwin");
        assertThat(groupedByLength.get(9)).containsExactly("Christine");
    }

}
