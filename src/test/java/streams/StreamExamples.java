package streams;

import static java.util.function.Function.identity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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

}
