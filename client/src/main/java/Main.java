import callbacks.PlayerCallbacks;
import common.amqp.client.AmqpClients;
import common.amqp.client.PlayerClient;
import common.amqp.config.Hosts;
import common.gameState.PlayerGameState;
import common.gameState.GameStates;
import gui.PlayerGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The client module entry point.
 */
public class Main {
    private static final String HOST = Hosts.LOCAL;
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static PlayerGameState state;
    private static PlayerClient client;
    private static PlayerGui gui;


    public static void main(String[] args) {
        LOGGER.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Client");
        LOGGER.info("Client started...");

        declareComponents();
        configureComponents();
        launchComponents();

        LOGGER.info("✓ Client ready, waiting for messages");
    }


    /**
     * First phase of the set-up. It creates the component used in the client.
     */
    private static void declareComponents() {
        state = GameStates.player();
        client = AmqpClients.player(HOST);
        gui = PlayerGui.of(client, state);
    }


    /**
     * Second phase of the set-up. Components are configured for the execution.
     */
    private static void configureComponents() {
        client.connect();
        client.addCallback(PlayerCallbacks.gameStateMsg(client, state, gui));
        client.addCallback(PlayerCallbacks.ackMsg(client, state, gui));
        client.listen();

        state.setCurrentPlayer(client.getPersonalQueueName());
    }


    /**
     * Last phase of the set-up. Components are executed.
     */
    private static void launchComponents() {
        client.listen();
        gui.launch();
    }
}
