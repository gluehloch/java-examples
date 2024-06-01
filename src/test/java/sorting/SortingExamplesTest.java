package sorting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class SortingExamplesTest {

    @Test
    void sortingExample() {
        List<String> strings = new ArrayList<>(List.of("Andre", "Christine", "Adam", "Lars", "Erwin"));
        strings.sort(Comparator.naturalOrder());
        assertSortedOrder(strings);

        Stream<String> streamOfStrings = strings.stream();
        assertSortedOrder(streamOfStrings.sorted(Comparator.naturalOrder()).toList());
    }

    private void assertSortedOrder(List<String> sorted) {
        assertThat(sorted).containsSequence(
                List.of("Adam", "Andre", "Christine", "Erwin", "Lars"));
    }

}