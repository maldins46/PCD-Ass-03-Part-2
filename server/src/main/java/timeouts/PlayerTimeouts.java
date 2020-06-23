package timeouts;

import common.client.GameClient;
import common.client.config.Destinations;
import common.gameState.ServerGameState;
import common.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Utility class to handle players timeout. When the
 * timeout expires, the player will be considered disconnected.
 */
public final class PlayerTimeouts {
    /**
     * Constant to specify the standard player timeout duration.
     */
    private static final int TIMEOUT_DURATION_IN_SECONDS = 60;

    /**
     * Service used to schedule timeouts.
     */
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    /**
     * Structure that memorizes the active timeouts, using a CompletableFuture.
     */
    private static final Map<Player, CompletableFuture<Void>> PLAYERS_TIMEOUTS = new HashMap<>();


    private PlayerTimeouts() { }

    /**
     * Calling this method, a timeout will be scheduled for the given player,
     * removing the player when the timer expires, except if the method will be
     * called again before the expiration.
     * @param player The player to process.
     * @param client The GameClient, used to respond to the player.
     * @param state The data structure, that will be modified after timeout
     *                 expiration.
     */
    public static void addOrUpdateTimer(final Player player, final GameClient client, final ServerGameState state) {

        if (PLAYERS_TIMEOUTS.containsKey(player)) {
            CompletableFuture<Void> oldTimeoutFuture = PLAYERS_TIMEOUTS.get(player);
            oldTimeoutFuture.complete(null);
        }

        final CompletableFuture<Void> newTimeoutFuture = schedulePlayerTimeout(() -> {
            state.removePlayer(player);
            Destinations.removePlayerQueue(player.getName());

            client.sendMessageToMatch(state.generateGameDataMsg());
        });

        PLAYERS_TIMEOUTS.put(player, newTimeoutFuture);
    }


    /**
     * Puts a timeout inside the executor, fixing the time in which will be
     * executed, and giving a CompletableFuture to interrupt it. Setting the
     * CompletableFuture as completed externally, will not execute the remove
     * player task, when timeout expires.
     * @param removePlayerTask What to do when the timer expires.
     * @return A completable future to interrupt the timeout. Setting it as
     *         completed will will not execute the remove player task.
     */
    private static CompletableFuture<Void> schedulePlayerTimeout(final Runnable removePlayerTask) {
        final CompletableFuture<Void> timeoutComplFuture = new CompletableFuture<>();

        SCHEDULED_EXECUTOR.schedule(() -> {
            if (!timeoutComplFuture.isDone()) {
                removePlayerTask.run();
                timeoutComplFuture.complete(null);
            }

        }, TIMEOUT_DURATION_IN_SECONDS, TimeUnit.SECONDS);

        return timeoutComplFuture;
    }
}
