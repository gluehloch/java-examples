package streams;

import static java.util.function.Function.identity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamExamples {

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

}
