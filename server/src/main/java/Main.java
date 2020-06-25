import callbacks.PuzzleServiceCallbacks;
import common.amqp.client.AmqpClients;
import common.amqp.client.PuzzleServiceClient;
import common.amqp.config.Hosts;
import common.gameState.GameStates;
import common.gameState.PuzzleServiceGameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The puzzle service entry point.
 */
public class Main {
    private static final String HOST = Hosts.LOCAL;
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static PuzzleServiceGameState gameState;
    private static PuzzleServiceClient client;


    public static void main(final String[] args) {
        LOGGER.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Server");
        LOGGER.info("Server started...");

        declareComponents();
        configureComponents();
        launchComponents();

        LOGGER.info("✓ Server ready, waiting for messages");
    }

    /**
     * First phase of the set-up. It creates the component used in the client.
     */
    private static void declareComponents() {
        gameState = GameStates.puzzleService();
        client = AmqpClients.puzzleService(HOST);
    }


    /**
     * Second phase of the set-up. Components are configured for the execution.
     */
    private static void configureComponents() {
        client.connect();
        client.addCallback(PuzzleServiceCallbacks.newPlayerMsg(client, gameState));
        client.addCallback(PuzzleServiceCallbacks.selectMsg(client, gameState));
        client.addCallback(PuzzleServiceCallbacks.swapRequestMsg(client, gameState));
        client.addCallback(PuzzleServiceCallbacks.rematchMsg(client, gameState));
    }


    /**
     * Last phase of the set-up. Components are executed.
     */
    private static void launchComponents() {
        client.listen();
    }
}
