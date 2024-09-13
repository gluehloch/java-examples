package misc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * ReduceVersusCollectExamples
 *
 * @author Andre Winkler
 * @since 2017
 */
public class ReduceVersusCollectExamples {

    @Test
    void joiningSomeStrings() {
        List<String> integers = integers(10); // TODO bei einem Wert von über 100 gibt es hier eine OutOfMemoryException

        long runtimeWithReduce = runtime(integers.parallelStream(), ReduceVersusCollectExamples::joinWithReduce);
        System.out.println("Runtime reduce seconds: %d".formatted(runtimeWithReduce));

        long runtimeWithCollect = runtime(integers.parallelStream(), ReduceVersusCollectExamples::joinWithCollect);
        System.out.println("Runtime collect seconds: %d".formatted(runtimeWithCollect));
    }

    public static String joinWithReduce(Stream<String> stream) {
        return stream.reduce(new StringBuffer(), StringBuffer::append, StringBuffer::append).toString();
    }

    public static String joinWithCollect(Stream<String> stream) {
        return stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    private long runtime(Stream<String> stream, Function<Stream<String>, String> streamFunction) {
        System.out.println("Memory: %d".formatted(Runtime.getRuntime().maxMemory()));

        LocalDateTime start = LocalDateTime.now();
        String joinReduce = streamFunction.apply(stream);
        System.out.println("Length: %d".formatted(joinReduce.length()));
        LocalDateTime end = LocalDateTime.now();

        System.out.println("Memory: %d".formatted(Runtime.getRuntime().maxMemory()));

        long seconds = ChronoUnit.MILLIS.between(start, end);
        return seconds;
    }

    private List<String> integers(int nums) {
        Random random = new Random();
        List<String> integers = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            integers.add(Integer.toString(random.nextInt()));
        }
        return integers;
    }

}
