package thread;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class FutureExample {

    /**
     * Create a future.
     * 
     * @return Future<String> A future
     * @throws InterruptedException
     *             ...
     */
    private CompletableFuture<String> calculateAsync()
            throws InterruptedException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    /**
     * Create a future with the static helper method. The future is already
     * completed. {@code #get()} will not wait on the result.
     * 
     * @return Future<String> A future
     * @throws InterruptedException
     *             ...
     */
    private CompletableFuture<String> calculateAsync2() {
        return CompletableFuture.completedFuture("Hello2");
    }

    @Test
    public void completableFuture() throws Exception {
        CompletableFuture<String> future = calculateAsync();

        String result = future.get();
        assertThat(result).isEqualTo("Hello");
        assertThat(future.isCompletedExceptionally()).isFalse();

        assertThat(calculateAsync2().get()).isEqualTo("Hello2");
    }

    private CompletableFuture<String> calculateAsyncWithCancellation()
            throws InterruptedException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            // Der Parameter in #cancel(boolean) hat keinen Einfluss.
            completableFuture.cancel(false);
            return null;
        });

        return completableFuture;
    }

    /**
     * Cancel execution of a {@link CompletableFuture}. The thread himself
     * cancels the {@link CompletableFuture}.
     *
     * @throws InterruptedException
     *             ...
     * @throws ExecutionException
     *             ...
     */
    @Test
    public void cancelCompletableFuture()
            throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = calculateAsyncWithCancellation();

        try {
            future.get(); // CancellationException
            fail("Expected CancellationException");
        } catch (CancellationException ex) {
            assertThat(future.isCancelled()).isTrue();
            assertThat(future.isCompletedExceptionally()).isTrue();
        }
    }

    /**
     * {@code CompletableFuture#runAsync(Runnable)} startet einen asynchronen
     * Task/Thread. Utility/Helper Methode um nicht einen Thread-Pool zu
     * eroeffnen. #runAsync liefert <b>kein</b> Ergebnis zurueck. Vergleiche
     * auch {@link #completeAsyncOfCompletableFuture()}.
     * 
     * @throws Exception
     *             ...
     */
    @Test
    public void runAsyncOfCompletableFuture() throws Exception {
        final StringBuilder sb = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            sb.append("Start calculation ...");
        });

        cf.get();
        assertThat(sb.toString()).isEqualTo("Start calculation ...");
    }

    /**
     * {@code CompletableFuture#completeAsync(java.util.function.Supplier)}
     * startet einen asynchronen Task/Thread. {@code #completeAsync()} liefert
     * ein Ergebnis zurueck. Vergleiche auch
     * {@link #runAsyncOfCompletableFuture()}.
     * 
     * @throws Exception
     *             ...
     */
    @Test
    public void completeAsyncOfCompletableFuture() throws Exception {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.completeAsync(() -> {
            return "Hallo";
        });
        assertThat(cf.get()).isEqualTo("Hallo");
    }

    @Test
    public void completableFutureWithMultipleParallelTasks() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture
                .supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture
                .allOf(future1, future2, future3);

        combinedFuture.get();

        assertThat(future1.isDone()).isTrue();
        assertThat(future2.isDone()).isTrue();
        assertThat(future3.isDone()).isTrue();
    }

    @Test
    public void composedCompletableFuture() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(() -> "Hello").thenComposeAsync(
                        s -> CompletableFuture.supplyAsync(() -> s + " world"));
        assertThat(future1.get()).isEqualTo("Hello world");

        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> "Hello").thenCompose(s -> CompletableFuture
                        .supplyAsync(() -> s + " world 2"));
        assertThat(future2.get()).isEqualTo("Hello world 2");

        CompletableFuture<String> cf = new CompletableFuture<>();

        CompletableFuture<String> compose1 = cf.completeAsync(() -> "Hallo")
                .thenCompose(
                        s -> CompletableFuture.supplyAsync(() -> s + " Andre"));
        assertThat(compose1.get()).isEqualTo("Hallo Andre");

        CompletableFuture<String> compose2 = cf.completeAsync(() -> "Hallo")
                .thenApply(s -> s + " Andre 2");
        assertThat(compose2.get()).isEqualTo("Hallo Andre 2");

    }

    /**
     * TODO
     * 
     * @throws Exception
     */
    @Test
    @Disabled
    public void acceptEither() throws Exception {
        CompletableFuture<String> cf = new CompletableFuture<>();
        CompletionStage<? extends String> other = null;
        Consumer<? super String> action = null;
        cf.acceptEither(other, action);
    }
}
