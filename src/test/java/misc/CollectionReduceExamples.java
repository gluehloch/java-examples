package misc;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import org.junit.Test;

/**
 * Test for some things: Collection to stream with reduce.
 *
 * @author Andre Winkler
 * @since 2017
 */
public class CollectionReduceExamples {

    @Test
    public void sumUpCollectionElements() {
        assertThat(Arrays.asList(10, 20, 30).stream()
                .reduce((result, element) -> result + element).get())
                        .isEqualTo(60);
        assertThat(Arrays.asList(10).stream()
                .reduce((result, element) -> result + element).get())
                        .isEqualTo(10);

        assertThat(Arrays.asList(10, 20, 30).stream()
                .reduce((result, element) -> result + element + 1).get())
                        .isEqualTo(62);
        assertThat(Arrays.asList(10).stream()
                .reduce((result, element) -> result + element + 1).get())
                        .isEqualTo(10);

        // Here comes the additional 'identity' parameter.
        assertThat(Arrays.asList(10, 20, 30).stream().reduce(0,
                (result, element) -> result + element + 1))
                        .isEqualTo(63);
        assertThat(Arrays.asList(10).stream().reduce(0,
                (result, element) -> result + element + 1))
                        .isEqualTo(11);
        assertThat(Arrays.asList(10).stream().reduce(1,
                (result, element) -> result + element + 1))
                        .isEqualTo(12);
        assertThat(new ArrayList<Integer>().stream().reduce(0,
                (result, element) -> result + element + 1))
                        .isEqualTo(0);

        Integer identity = 0;
        BiFunction<Integer, Integer, Integer> accumulator = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
        };
        BinaryOperator<Integer> combiner = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
        };
        Arrays.asList(10, 20, 30).stream().reduce(identity, accumulator,
                combiner);
    }

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
        List<Integer> integers = null;
        int summe = 0;

        integers = Arrays.asList(100);
        summe = integers.stream()
                .reduce((result, element) -> result + element).get();
        assertThat(summe).isEqualTo(100);

        integers = Arrays.asList(100);
        summe = integers.stream()
                .reduce(0, (result, element) -> result + element);
        assertThat(summe).isEqualTo(100);

        summe = integers.stream()
                .reduce((result, element) -> result + 4711 + element).get();

        assertThat(summe).isEqualTo(100); // Und nicht: 100 + 4711!
        assertThat(summe).isEqualTo(100);

        summe = integers.stream()
                .reduce(0, (result, element) -> result + 4711 + element);
        assertThat(summe).isEqualTo(4811); // Jetzt aber: 100 + 4711!
        assertThat(summe).isEqualTo(4811);

        integers = Arrays.asList(100, 200);
        summe = integers.stream()
                .reduce((result, element) -> result + 4711 + element).get();
        assertThat(summe).isEqualTo(5011);

        integers = Arrays.asList(100);
        summe = integers.stream()
                .reduce(new MyBinaryOperator(100)).get();
        assertThat(summe).isEqualTo(100);

        integers = Arrays.asList(100);
        summe = integers.stream()
                .reduce(0, new MyBinaryOperator(200));
        assertThat(summe).isEqualTo(300);

        integers = Arrays.asList(100, 200);
        summe = integers.stream()
                .reduce(new MyBinaryOperator(100)).get();
        assertThat(summe).isEqualTo(400);
    }

    @Test
    public void sumUpStringLength() {
        List<String> strings = Arrays.asList("Andre", "Christine", "Adam",
                "Lars", "Erwin");
        
        // Warum???
        assertThat(strings.stream().reduce(0,
                (Integer sum, String string) -> string.length(),
                (Integer sum1, Integer sum2) -> sum1 + sum2)).isEqualTo(5);

        // Sollte doch eigentlich ...
        assertThat(strings.stream().reduce(0,
                (sum, string) -> sum + string.length(),
                (sum1, sum2) -> sum1 + sum2)).isEqualTo(23);

        strings = Arrays.asList("Andre", "Christine", "Adam", "Lars", "Erwin");
        assertThat(strings.stream().reduce(0,
                (Integer sum, String string) -> sum + string.length(),
                (Integer sum1, Integer sum2) -> sum1 + sum2).intValue()).isEqualTo(27);
    }

    @Test
    public void sumUpAllCollectionElementsAsOneLiner() {
        List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        int sum = integers.stream()
                .reduce((result, element) -> result + element).get();

        assertThat(sum).isEqualTo(45);
        System.out.printf("Summe: %1$d%n", sum);
    }

    private class MyBinaryOperator implements BinaryOperator<Integer> {
        private int addme;

        public MyBinaryOperator(int addme) {
            this.addme = addme;
        }

        @Override
        public Integer apply(Integer t, Integer u) {
            return Integer.sum(t + addme, u);
        }
    }

}
