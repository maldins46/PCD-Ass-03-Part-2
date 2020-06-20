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
        serverState = GameStates.serverGameState();
        client = new GameClient.Builder()
                .addHost(host)
                .addCallback(Callbacks.newPlayerMsg(client, serverState))
                .addCallback(Callbacks.selectMsg(client, serverState))
                .addCallback(Callbacks.swapRequestMsg(client, serverState))
                .addCallback(Callbacks.rematchMsg(client, serverState))
                .build();

        logger.info("Connecting to the RabbitMQ Broker with host " + host + "...");
        client.connect();

        logger.info("✓ Server ready, waiting for messages");
    }
}
