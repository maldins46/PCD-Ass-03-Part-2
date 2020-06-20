import common.client.GameClient;
import common.client.config.Hosts;
import common.model.GameData;
import callbacks.Callbacks;
import model.ServerGameData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static GameData data;
    private static GameClient client;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);



    public static void main(final String[] args) {
        logger.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Server");
        logger.info("Server started...");

        final String host = Hosts.LOCAL;
        data = new ServerGameData();
        client = new GameClient.Builder()
                .addHost(host)
                .addCallback(Callbacks.newPlayerMsg(client, data))
                .addCallback(Callbacks.selectMsg(client, data))
                .addCallback(Callbacks.swapRequestMsg(client, data))
                .addCallback(Callbacks.rematchMsg(client, data))
                .build();

        logger.info("Connecting to the RabbitMQ Broker with host " + host + "...");
        client.connect();

        logger.info("✓ Server ready, waiting for messages");
    }
}
