package communication;

public final class Destinations {
    public static final String SERVER_QUEUE_NAME = "server-queue";
    public static final String MATCH_TOPIC_NAME = "match-topic";


    public static boolean isKnownDestination(final String destination) {
        return SERVER_QUEUE_NAME.equals(destination) || MATCH_TOPIC_NAME.equals(destination);
    }
}