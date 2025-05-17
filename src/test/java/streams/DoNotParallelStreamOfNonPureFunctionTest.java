package streams;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class DoNotParallelStreamOfNonPureFunctionTest {

    @Test
    void doNotParallelStreamOfNonPureFunctions() {
        // This is a non-pure function
        List<String> list = new ArrayList<>();
        final List<String> constructedList = new ArrayList<>();
        Stream.of("a", "b", "c").parallel().forEach(constructedList::add);
        assertThat(constructedList).hasSize(3).contains("a", "b", "c");


        // This is a pure function
        Stream.of("a", "b", "c").forEach(list::add);
        // This is a non-pure function
        Stream.of("a", "b", "c").parallel().forEachOrdered(list::add);
    }

}
