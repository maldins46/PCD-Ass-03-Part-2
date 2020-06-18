package communication;

public final class Hosts {
    public static final String LOCAL = "localhost";
    public static final String INTERNAL_RABBIT = "rabbit";


    public static boolean isKnownHost(final String host) {
        return LOCAL.equals(host) || INTERNAL_RABBIT.equals(host);
    }
}
