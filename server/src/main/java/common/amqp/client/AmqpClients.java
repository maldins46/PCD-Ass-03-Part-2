package common.amqp.client;

/**
 * Factory method used to expose the AMQP clients in use.
 */
public final class AmqpClients {
    private AmqpClients() { }

    public static PuzzleServiceClient puzzleService(final String host) {
        return new PuzzleServiceClientImpl(host);
    }

    public static PlayerClient player(final String host) {
        return new PlayerClientImpl(host);
    }
}
