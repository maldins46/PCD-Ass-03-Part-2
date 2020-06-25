package common.amqp.config;

/**
 * It contains all known destinations used by the broker for this project,
 * and gives utility methods about them.
 */
public final class Destinations {
    /**
     * The destination used to refer to the service.
     */
    public static final String PUZZLE_SERVICE_QUEUE_NAME = "server-queue";

    /**
     * The destination used as the topic in which every player
     * is listening. In AMQP protocol, it is used as the exchange name.
     */
    public static final String MATCH_TOPIC_NAME = "match-topic";


    private Destinations() { }
}
