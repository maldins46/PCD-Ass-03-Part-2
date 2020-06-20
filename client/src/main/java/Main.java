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
        logger.info("Connecting to the RabbitMQ Broker with host " + host + "...");

        gui = PuzzleGui.of();
        clientState = GameStates.clientGameState();
        client = GameClient.of(host, false);
        client.connect();
        client.addCallback(Callbacks.gameStateMsg(client, clientState, gui));
        client.addCallback(Callbacks.ackMsg(client, clientState, gui));
        client.listen();
        logger.info("✓ Client ready, waiting for messages");

    }
}
