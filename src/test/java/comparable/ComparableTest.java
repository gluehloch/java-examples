package comparable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

class ComparableTest {

    @Test
    void createComparableForComplexType() {
        Comparator<Reference> comparator = Comparator.comparing(Reference::group)
                .thenComparing(Reference::key)
                .thenComparing(Reference::version);

        List<Reference> references = List.of(
                new Reference("com.example", "libraryA", "1.0.0"),
                new Reference("com.example", "libraryA", "1.0.1"),
                new Reference("de.awtools", "dbload", "1.0-SNAPSHOT"),
                new Reference("com.example", "libraryB", "2.0.0"),
                new Reference("org.example", "libraryC", "3.0.0"));

        List<Reference> sortedReferences = references.stream().sorted(comparator).toList();

        assertThat(sortedReferences).containsExactly(
                new Reference("com.example", "libraryA", "1.0.0"),
                new Reference("com.example", "libraryA", "1.0.1"),
                new Reference("com.example", "libraryB", "2.0.0"),
                new Reference("de.awtools", "dbload", "1.0-SNAPSHOT"),
                new Reference("org.example", "libraryC", "3.0.0"));
    }

    record Reference(String group, String key, String version) {
    }

    private static class ReferenceComparator implements Comparator<ComparableTest.Reference> {
        @Override
        public int compare(ComparableTest.Reference r1, ComparableTest.Reference r2) {
            int gruppeComparison = r1.group().compareTo(r2.group());
            if (gruppeComparison != 0) {
                return gruppeComparison;
            }

            int schluesselComparison = r1.key().compareTo(r2.key());
            if (schluesselComparison != 0) {
                return schluesselComparison;
            }

            return r1.version().compareTo(r2.version());
        }
    }
}
