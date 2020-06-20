import common.client.GameClient;
import common.client.config.Hosts;
import callbacks.Callbacks;
import common.gameState.GameStates;
import common.gameState.ModifiableGameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static ModifiableGameState serverState;
    private static GameClient client;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(final String[] args) {
        logger.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Server");
        logger.info("Server started...");

        final String host = Hosts.LOCAL;
        logger.info("Connecting to the RabbitMQ Broker with host " + host + "...");

        serverState = GameStates.serverGameState();
        client = GameClient.of(host, true);
        client.connect();
        client.addCallback(Callbacks.newPlayerMsg(client, serverState));
        client.addCallback(Callbacks.selectMsg(client, serverState));
        client.addCallback(Callbacks.swapRequestMsg(client, serverState));
        client.addCallback(Callbacks.rematchMsg(client, serverState));
        client.listen();
        logger.info("✓ Server ready, waiting for messages");
    }
}
