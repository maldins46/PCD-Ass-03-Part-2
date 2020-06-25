package common.amqp.config;

/**
 * It contains all known hosts, used by the broker in this particular
 * project. Localhost is used for tests, meanwhile rabbit is used in
 * the Docker configuration.
 */
public final class Hosts {
    /**
     * This is the host used for tests. This is useful for example
     * when the RabbitMQ broker do not run on Docker, but in the
     * local machine, or when the service is configured in debug mode.
     */
    public static final String LOCAL = "localhost";

    /**
     * The deployment host. It is the DNS name assigned to the Docker
     * container, in the internal environment of the server.
     */
    public static final String INTERNAL_RABBIT = "rabbit";


    private Hosts() { }


    /**
     * Checks whether the passed host is one of the standard definitions
     * used as part of the project.
     * @param host The host to consider.
     * @return True if is known; false otherwise.
     */
    public static boolean isKnownHost(final String host) {
        return LOCAL.equals(host) || INTERNAL_RABBIT.equals(host);
    }
}
