package misc;

import java.util.List;

/**
 * Some new Java 9 / 10 features.
 * 
 * @author Andre Winkler
 * @since 2018
 */
public class CollectionExamples {

    public void examples() {
        var integers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integers2.sort((a, b) -> a.compareTo(b));
    }

}
