package client.config;

public final class Hosts {
    public static final String LOCAL = "localhost";
    public static final String INTERNAL_RABBIT = "rabbit";

    private Hosts() { }


    public static boolean isKnownHost(final String host) {
        return LOCAL.equals(host) || INTERNAL_RABBIT.equals(host);
    }
}
