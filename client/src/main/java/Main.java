import callbacks.Callbacks;
import common.amqp.client.AmqpClients;
import common.amqp.client.PlayerClient;
import common.amqp.config.Hosts;
import common.gameState.PlayerGameState;
import common.gameState.GameStates;
import gui.GameGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static PlayerGameState state;
    private static PlayerClient client;
    private static GameGui gui;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Assignment 03, pt.2 - Maldini, Gorini, Angelini - MultiPlayer Puzzle Client");
        logger.info("Client started...");

        final String host = Hosts.LOCAL;
        state = GameStates.player();
        client = AmqpClients.player(host);
        gui = GameGui.of(client, state);

        client.connect();
        client.addCallback(Callbacks.gameStateMsg(client, state, gui));
        client.addCallback(Callbacks.ackMsg(client, state, gui));

        state.setCurrentPlayer(client.getPersonalQueueName());
        client.listen();
        gui.launch();
        logger.info("✓ Client ready, waiting for messages");
    }
}
