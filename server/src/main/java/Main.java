import client.GameClient;
import client.config.Hosts;
import model.GameData;
import callbacks.Callbacks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static GameData gameData;
    private static GameClient gameClient;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);



    public static void main(final String[] args) {

        logger.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Server");
        logger.info("Server started...");

        final String host = Hosts.LOCAL;

        gameData = new GameData();
        gameClient = new GameClient.Builder()
                .addHost(host)
                .addCallback(Callbacks.newPlayerMsg(gameClient, gameData))
                .addCallback(Callbacks.selectMsg(gameClient, gameData))
                .addCallback(Callbacks.swapRequestMsg(gameClient, gameData))
                .addCallback(Callbacks.rematchMsg(gameClient, gameData))
                .build();

        logger.info("Connecting to the RabbitMQ Broker with host " + host + "...");
        gameClient.connect();
        logger.info("✓ Server ready, waiting for messages");
    }
}
