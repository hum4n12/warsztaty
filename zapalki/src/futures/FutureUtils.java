package futures;

import java.util.concurrent.*;

public class FutureUtils {
    public static CompletableFuture<Void> executeNumberOfTimes(Runnable task, int period, int executions) {
        return convert((Future<Void>)Executors.newSingleThreadScheduledExecutor()
                .submit(() -> {
                    for (int i=0;i<executions;i++) {
                        task.run();
                        try {
                            Thread.sleep(period);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }));
    }

    public static <T> CompletableFuture<T> schedule(Callable<T> task) {
        Future<T> future = Executors.newSingleThreadExecutor().submit(task);
        return convert(future);
    }
    public static <T> CompletableFuture<T> schedule(Runnable task) {
        return schedule(() -> {
            task.run();
            return null;
        });
    }

    private static <T> CompletableFuture<T> convert(Future<T> future) {
        int CHECK_DELAY = 50;
        int CHECK_PERIOD = 100;
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        Runnable task = () -> {
            while (true) {
                if (future.isDone()) {
                    try {
                        completableFuture.complete(future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        completableFuture.completeExceptionally(e);
                    }
                    break;
                } else if (future.isCancelled()) {
                    completableFuture.completeExceptionally(new RuntimeException("Future was cancelled"));
                    break;
                } else {
                    try {
                        Thread.sleep(CHECK_PERIOD);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Executors.newSingleThreadScheduledExecutor().schedule(task, CHECK_DELAY, TimeUnit.MILLISECONDS);
        return completableFuture;
    }
}
