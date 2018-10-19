package thread;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

public class FutureExample {

    /**
     * Create a future.
     * 
     * @return Future<String> A future
     * @throws InterruptedException
     *             ...
     */
    private Future<String> calculateAsync() throws InterruptedException {
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
    private Future<String> calculateAsync2() {
        return CompletableFuture.completedFuture("Hello2");
    }

    @Test
    public void completableFuture() throws Exception {
        Future<String> future = calculateAsync();
        String result = future.get();
        assertThat(result).isEqualTo("Hello");

        assertThat(calculateAsync2().get()).isEqualTo("Hello2");
    }

    private Future<String> calculateAsyncWithCancellation()
            throws InterruptedException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.cancel(false);
            return null;
        });

        return completableFuture;
    }

    @Test
    public void cancelCompletableFuture()
            throws InterruptedException, ExecutionException {

        Future<String> future = calculateAsyncWithCancellation();

        try {
            future.get(); // CancellationException
            fail("Expected CancellationException");
        } catch (CancellationException ex) {
            // ok
        }
    }

}
