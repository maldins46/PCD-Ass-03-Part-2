package common.client.config;

/**
 * Server host name.
 */
public final class Hosts {
    public static final String LOCAL = "localhost";
    public static final String INTERNAL_RABBIT = "rabbit";

    private Hosts() { }

    /**
     * Responds if a host is known.
     * @param host The host to consider.
     * @return True if is known, false otherwise.
     */
    public static boolean isKnownHost(final String host) {
        return LOCAL.equals(host) || INTERNAL_RABBIT.equals(host);
    }
}
