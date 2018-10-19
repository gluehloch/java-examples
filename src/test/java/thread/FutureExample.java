package thread;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

public class FutureExample {

    /**
     * Create a future.
     * 
     * @return Future<String> A future
     * @throws InterruptedException ...
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
     * Create a future with the static helper method.
     * 
     * @return Future<String> A future
     * @throws InterruptedException ...
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

}
