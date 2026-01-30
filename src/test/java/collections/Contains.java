package collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class Contains {

    @Test
    void containsAnotherCollection() {
        {
            final List<String> list = List.of("a", "b", "c", "d");
            final List<String> anotherList = List.of("a", "b", "c", "d");

            assertThat(list).containsAll(anotherList);
            assertThat(anotherList).containsAll(list);

            assertThat(list.stream().allMatch(anotherList::contains)).isTrue();
            assertThat(anotherList.stream().allMatch(list::contains)).isTrue();
        }

        {
            final List<String> list = List.of("a", "b", "c", "d");
            final List<String> anotherList = List.of("a", "b", "c");

            assertThat(list).containsAll(anotherList);
            // assertThat(anotherList).containsAll(list); -> false

            // assertThat(list.stream().allMatch(anotherList::contains)).isTrue(); -> false
            assertThat(anotherList.stream().allMatch(list::contains)).isTrue();
        }
    }

}
