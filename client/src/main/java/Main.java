import callbacks.Callbacks;
import common.client.GameClient;
import common.client.config.Hosts;
import common.gameState.GameStates;
import common.gameState.ReplaceableGameState;
import gui.PuzzleGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static ReplaceableGameState clientState;
    private static GameClient client;
    private static PuzzleGui gui;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Client");
        logger.info("Client started...");

        final String host = Hosts.LOCAL;
        gui = PuzzleGui.of();
        clientState = GameStates.clientGameState();
        client = new GameClient.Builder()
                .addHost(host)
                .addCallback(Callbacks.gameStateMsg(client, clientState, gui))
                .build();

        logger.info("Connecting to the RabbitMQ Broker with host " + host + "...");
        client.connect();

        client.addCallback(Callbacks.ackMsg(client, clientState, gui));

        logger.info("✓ Client ready, waiting for messages");

    }
}
