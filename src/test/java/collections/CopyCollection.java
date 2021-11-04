package collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CopyCollection {

    @DisplayName("Example: Copy a collection")
    @Tag("collections")
    @Test
    void copyCollections() {
        List<String> strings = List.of("Andre", "Christine", "Adam", "Lars", "Erwin");
        List<String> copy = new ArrayList<>(strings);
        assertThat(copy).containsExactlyElementsOf(strings);
        
        // Achtung: Arrays.asList liefert nicht den generischen Typen zurzueck.
        List<Object> strings2 = Arrays.asList(new String[] {"Andre", "Christine", "Adam", "Lars", "Erwin"});
        assertThat(strings2).hasSize(strings2.size());

        List<Object> destination = Arrays.asList(new String[] {"1", "2", "3", "4", "5", "6"});
        // Achtung: Erstellt keine Kopie wie im oberen Beispiel. Das Element "6" wird in der 
        // 'destination' bei behalten.
        Collections.copy(destination, strings2);
        
        assertThat(destination).hasSize(6);
        assertThat(destination).containsExactly("Andre", "Christine", "Adam", "Lars", "Erwin", "6");
        
        List<String> strings3 = strings.stream().collect(Collectors.toList());
        assertThat(strings3).containsExactlyElementsOf(strings3);
    }

}
