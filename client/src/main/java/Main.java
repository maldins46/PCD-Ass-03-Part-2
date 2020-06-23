import callbacks.Callbacks;
import common.client.GameClient;
import common.client.config.Hosts;
import common.gameState.ClientGameState;
import common.gameState.GameStates;
import gui.PuzzleGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static ClientGameState clientState;
    private static GameClient client;
    private static PuzzleGui gui;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Client");
        logger.info("Client started...");

        final String host = Hosts.LOCAL;
        clientState = GameStates.clientGameState();
        client = GameClient.of(host, false);
        gui = PuzzleGui.of(client, clientState);

        client.connect();
        client.addCallback(Callbacks.gameStateMsg(client, clientState, gui));
        client.addCallback(Callbacks.ackMsg(client, clientState, gui));

        client.listen();
        gui.launch();
        logger.info("✓ Client ready, waiting for messages");

    }
}
