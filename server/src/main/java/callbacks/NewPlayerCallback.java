package callbacks;


import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.Message;
import common.amqp.messages.NewPlayerMsg;
import common.gameState.PuzzleServiceGameState;

/**
 * The callback used when a new player notifies the will to join the game.
 */
final class NewPlayerCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param state the game data.
     */
    NewPlayerCallback(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        super(client, state);
    }


    @Override
    public Class<? extends Message> getMessageType() {
        return NewPlayerMsg.class;
    }


    @Override
    public void executeBody(final Message rawMessage) {
        final NewPlayerMsg message = (NewPlayerMsg) rawMessage;
        getState().addPlayer(message.getPlayer());
    }
}
