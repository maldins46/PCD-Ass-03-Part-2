package common.amqp.client;

/**
 * Factory used to create the AMQP clients used by the plr and the
 * puzzle service.
 */
public final class AmqpClients {

    private AmqpClients() { }

    /**
     * Creates the client used by the puzzle service module.
     * @param host The host assigned to the client.
     * @return The client instance.
     */
    public static PuzzleServiceClient puzzleService(final String host) {
        return new PuzzleServiceClientImpl(host);
    }


    /**
     * Creates the client used by the player module.
     * @param host The host assigned to the client.
     * @return The client instance.
     */
    public static PlayerClient player(final String host) {
        return new PlayerClientImpl(host);
    }
}
