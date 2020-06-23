package common.client.config;

import java.util.HashSet;
import java.util.Set;

/**
 * It contains all known destinations used by the broker for this project,
 * and gives utility methods about them.
 */
public final class Destinations {
    private Destinations() { }

    /**
     * The destination used to refer to the server.
     */
    public static final String SERVER_QUEUE_NAME = "server-queue";

    /**
     * The destination used as the topic in which every player
     * is listening.
     */
    public static final String MATCH_TOPIC_NAME = "match-topic";

    /**
     * The queue used by specific players. useful for ack sending.
     */
    public static final Set<String> PLAYERS_QUEUE = new HashSet<>();

    /**
     * Memorizes the last broker-assigned queue, derived from a topic subscription.
     */
    public static String PERSONAL_QUEUE = "";


    /**
     * Returns true if the destination is known.
     * @param destination The given destination.
     * @return True if the destination is known, false otherwise.
     */
    public static boolean isKnownDestination(final String destination) {
        return SERVER_QUEUE_NAME.equals(destination)
                || MATCH_TOPIC_NAME.equals(destination)
                || PLAYERS_QUEUE.contains(destination)
                || PERSONAL_QUEUE.equals(destination);
    }


    public static boolean isPlayerQueue(final String destination) {
        return PLAYERS_QUEUE.contains(destination)  ;
    }


    /**
     * Adds a player queue to the known destinations.
     * @param destination The given destination.
     */
    public static void addPlayerQueue(final String destination) {
        PLAYERS_QUEUE.add(destination);
    }


    /**
     * Removes a player queue to the known destinations.
     * @param destination The given destination.
     */
    public static void removePlayerQueue(final String destination) {
        PLAYERS_QUEUE.remove(destination);
    }

    public static void setPersonalQueue(final String queueName) {
        PERSONAL_QUEUE = queueName;
    }
}
