package playerTimers;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class PlayerTimers {
    private static PlayerTimers instance;

    private final ScheduledExecutorService schedulerExecutor = Executors.newScheduledThreadPool(10);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    // todo players structure


    private PlayerTimers() {

    }

    public static PlayerTimers getInstance() {
        if (instance == null) {
            instance = new PlayerTimers();
        }
        return instance;
    }


    public void addOrUpdateTimer(String player) {
        // todo reschedule

        CompletableFuture<String> a = supplyAsync(() -> "hi", 1,
                TimeUnit.SECONDS, "default");
    }




    public <T> CompletableFuture<T> supplyAsync(final Supplier<T> supplier,
                                                       long timeoutValue,
                                                       TimeUnit timeUnit,
                                                       T defaultValue) {

        final CompletableFuture<T> cf = new CompletableFuture<T>();

        // as pointed out by Peti, the ForkJoinPool.commonPool() delivers a
        // ForkJoinTask implementation of Future, that doesn't interrupt when cancelling
        // Using Executors.newCachedThreadPool instead in the example
        // submit task
        Future<?> future = executorService.submit(() -> {
            try {
                cf.complete(supplier.get());
            } catch (Throwable ex) {
                cf.completeExceptionally(ex);
            }
        });

        //schedule watcher
        schedulerExecutor.schedule(() -> {
            if (!cf.isDone()) {
                cf.complete(defaultValue);
                future.cancel(true);
            }

        }, timeoutValue, timeUnit);

        return cf;
    }

}
