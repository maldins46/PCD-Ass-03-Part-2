package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;

import common.client.messages.Message;
import common.client.messages.AckMsg;
import common.client.messages.Messages;
import common.gameState.ServerGameState;
import common.model.Player;
import timeouts.PlayerTimeouts;

/**
 * The class is a base for callback.
 */
abstract class GenericServerCallback implements CtxCallback {

    /**
     * Server for the callback.
     */
    private final GameClient client;

    /**
     * The game data.
     */
    private final ServerGameState gameState;


    /**
     * Construct a generic callback with server and data.
     * @param client The game server.
     * @param gameState The game data.
     */
    GenericServerCallback(final GameClient client, final ServerGameState gameState) {
        this.client = client;
        this.gameState = gameState;
    }


    @Override
    public final void execute(final Message rawMessage) {
        executeBody(rawMessage);
        terminate(rawMessage);
    }


    /**
     * Every callback inside the server has a final part in model.common: it has to
     * update the player timer, send the update in the match topic and send
     * the ack to the player. This method handles it.
     * @param message The received message.
     */
    private void terminate(final Message message) {
        final Player senderPlayer = Player.of(message.getSender());

        PlayerTimeouts.addOrUpdateTimer(senderPlayer, client, gameState);
        final AckMsg ack = Messages.createAckMsg(senderPlayer);
        client.sendMessageToMatch(gameState.generateGameDataMsg());
        client.sendMessageToPlayer(Player.of(message.getSender()), ack);
    }


    /**
     * This is the core part of the callback, different for each received
     * message. Do not include the termination part in it.
     * @param rawMessage The received message.
     */
    protected abstract void executeBody(Message rawMessage);


    /**
     * Getter for the game data instance, for child classes.
     * @return The game data.
     */
    protected ServerGameState getGameState() {
        return gameState;
    }
}
