package comparable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

class ComparableTest {

    private final Reference ref1 = new Reference("com.example", "libraryA", "1.0.1");
    private final Reference ref2 = new Reference("com.example", "libraryA", "1.0.0");
    private final Reference ref3 = new Reference("de.awtools", "dbload", "1.0-SNAPSHOT");
    private final Reference ref4 = new Reference("com.example", "libraryB", "2.0.0");
    private final Reference ref5 = new Reference("org.example", "libraryC", "3.0.0");

    private final Comparator<Reference> comparator = Comparator.comparing(Reference::group)
            .thenComparing(Reference::key)
            .thenComparing(Reference::version);

    @Test
    void createComparableForComplexType() {
        List<Reference> references = List.of(ref1, ref2, ref3, ref4, ref5);
        List<Reference> sortedReferences = references.stream().sorted(comparator).toList();
        assertThat(sortedReferences).hasSize(5).containsExactly(ref2, ref1, ref4, ref3, ref5);
        assertThat(references.stream()
                .sorted(new ReferenceComparator()))
                .hasSize(5)
                .containsExactly(ref2, ref1, ref4, ref3, ref5);
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
