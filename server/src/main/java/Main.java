import common.client.GameClient;
import common.client.config.Hosts;
import callbacks.Callbacks;
import common.gameState.GameStates;
import common.gameState.ServerGameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static ServerGameState state;
    private static GameClient client;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(final String[] args) {
        logger.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Server");
        logger.info("Server started...");

        final String host = Hosts.LOCAL;
        state = GameStates.serverGameState();
        client = GameClient.of(host, true);
        client.connect();
        client.addCallback(Callbacks.newPlayerMsg(client, state));
        client.addCallback(Callbacks.selectMsg(client, state));
        client.addCallback(Callbacks.swapRequestMsg(client, state));
        client.addCallback(Callbacks.rematchMsg(client, state));
        client.listen();
        logger.info("✓ Server ready, waiting for messages");
    }
}
