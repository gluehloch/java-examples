package de.awtools.functionalprogramming;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * {@link "https://8thlight.com/blog/brian-gerstle/2019/01/22/fnl-exceptions-in-java.html"}
 *
 * @param <V> the result type
 * @param <E> an exception
 */
public class Result<V, E extends Throwable> {

    private final V value;
    private final E error;

    private Result(V value, E error) {
        this.value = value;
        this.error = error;
    }

    private static <V, E extends Throwable> Result<V, E> failure(E error) {
        return new Result<>(null, Objects.requireNonNull(error));
    }

    private static <V, E extends Throwable> Result<V, E> success(V value) {
        return new Result<>(Objects.requireNonNull(value), null);
    }

    // ----

    public static <V, E extends Throwable> Result<V, E> attempt(CheckedSupplier<? extends V, ? extends E> p) {
        try {
            return Result.success(p.get());
        } catch (Throwable e) {
            @SuppressWarnings("unchecked")
            E err = (E) e;
            return Result.failure(err);
        }
    }

    @FunctionalInterface
    public interface CheckedSupplier<V, E extends Throwable> {
        V get() throws E;
    }

    public <T> Result<T, E> map(Function<? super V, ? extends T> mapper) {
        return Optional.ofNullable(error)
                .map(e -> Result.<T, E>failure(e))
                .orElseGet(() -> Result.success(mapper.apply(value)));
    }

    public V orElseThrow() throws E {
        return Optional.ofNullable(value).orElseThrow(() -> error);
    }

}
