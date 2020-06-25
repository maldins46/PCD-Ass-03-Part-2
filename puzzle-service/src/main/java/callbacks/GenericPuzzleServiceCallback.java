package callbacks;

import common.amqp.callback.PuzzleServiceCallback;
import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.AckMsg;
import common.amqp.messages.Message;
import common.amqp.messages.Messages;
import common.amqp.messages.PlayerMsg;
import common.gameState.PuzzleServiceGameState;
import common.model.Player;
import timeouts.PlayerTimeouts;

/**
 * The class is a base for callback.
 */
abstract class GenericPuzzleServiceCallback implements PuzzleServiceCallback {

    /**
     * Server for the callback.
     */
    private final PuzzleServiceClient client;

    /**
     * The game data.
     */
    private final PuzzleServiceGameState gameState;


    /**
     * Construct a generic callback with server and data.
     * @param client The game server.
     * @param gameState The game data.
     */
    GenericPuzzleServiceCallback(final PuzzleServiceClient client, final PuzzleServiceGameState gameState) {
        this.client = client;
        this.gameState = gameState;
    }


    @Override
    public final void execute(final Message rawMessage) {
        final PlayerMsg message = (PlayerMsg) rawMessage;
        executeBody(message);
        terminate(message);
    }


    /**
     * Every callback inside the server has a final part in model.common: it has to
     * update the player timer, send the update in the match topic and send
     * the ack to the player. This method handles it.
     * @param message The received message.
     */
    private void terminate(final PlayerMsg message) {
        final Player senderPlayer = message.getSenderPlayer();

        PlayerTimeouts.addOrUpdateTimeout(senderPlayer, client, gameState);
        final AckMsg ack = Messages.createAckMsg();
        client.sendMessageToMatch(gameState.generateGameDataMsg());
        client.sendMessageToPlayer(senderPlayer, ack);
    }


    /**
     * This is the core part of the callback, different for each received
     * message. Do not include the termination part in it.
     * @param rawMessage The received message.
     */
    protected abstract void executeBody(PlayerMsg rawMessage);


    /**
     * Getter for the game data instance, for child classes.
     * @return The game data.
     */
    protected PuzzleServiceGameState getGameState() {
        return gameState;
    }
}
