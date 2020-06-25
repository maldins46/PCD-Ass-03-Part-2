package callbacks;


import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.NewPlayerMsg;
import common.amqp.messages.PlayerMsg;
import common.gameState.PuzzleServiceGameState;

/**
 * The callback used when a new player notifies the will to join the game.
 */
final class NewPlayerCallback extends GenericPuzzleServiceCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param gameState the game data.
     */
    NewPlayerCallback(final PuzzleServiceClient client, final PuzzleServiceGameState gameState) {
        super(client, gameState);
    }


    @Override
    public Class<? extends PlayerMsg> getMessageType() {
        return NewPlayerMsg.class;
    }


    @Override
    public void executeBody(final PlayerMsg rawMessage) {
        final NewPlayerMsg message = (NewPlayerMsg) rawMessage;
        getGameState().addPlayer(message.getPlayer());
    }
}
