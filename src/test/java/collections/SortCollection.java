package collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SortCollection {

    @DisplayName("Example: Sort a collection")
    @Tag("collections")
    @Test
    void sortCollection() {
        List<String> strings = new ArrayList<>();
        strings.addAll(List.of("test1:struktur", "test2:historie", "test3.xml", "test3:struktur"));
        
        Collections.sort(strings, new StringComparator());
        
        assertThat(strings).containsExactly("test3.xml", "test1:struktur", "test3:struktur", "test2:historie");
    }

    private static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
        
    }
    
}
