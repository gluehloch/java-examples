package misc;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Test for some things: Collection to stream with reduce.
 *
 * @author Andre Winkler
 * @since 2017
 */
public class CollectionReduceExamples {

    @Test
    public void sumUpAllCollectionElements() {
        List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        int sum = integers.stream()
                .reduce((Integer result, Integer element) -> {
                    return result + element;
                }).get();

        assertThat(sum).isEqualTo(45);
        System.out.printf("Summe: %1$d%n", sum);
    }

    @Test
    public void sumUpAllCollectionElements2() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sum = integers.stream()
                .reduce((Integer result, Integer element) -> {
                    return result + element;
                }).get();

        assertThat(sum).isEqualTo(55);
        System.out.printf("Summe: %1$d%n", sum);
    }

    @Test
    public void sumUpSingleElementCollection() {
        List<Integer> integers = Arrays.asList(100);

        int sum = integers.stream()
                .reduce((Integer result, Integer element) -> {
                    return result + element;
                }).get();

        assertThat(sum).isEqualTo(100);
        System.out.printf("Summe: %1$d%n", sum);
    }

    @Test
    public void sumUpAllCollectionElementsAsOneLiner() {
        List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11);

        int sum = integers.stream()
                .reduce((result, element) -> result + element).get();

        assertThat(sum).isEqualTo(66);
        System.out.printf("Summe: %1$d%n", sum);
    }

    @Test
    public void sumUpSingleElementCollection2() {
        List<Integer> integers = Arrays.asList(100);

        int sum = integers.stream()
                .reduce((result, element) -> result + 4711 + element).get();

        assertThat(sum).isEqualTo(100);
        System.out.printf(
                "Summe: %1$d. Also nicht wie man vielleicht erwarten könnte 100 + 4711.%n",
                sum);
    }

}
