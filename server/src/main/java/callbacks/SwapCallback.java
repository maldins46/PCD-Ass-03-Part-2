package callbacks;

import common.client.GameClient;
import common.client.messages.Message;
import common.client.messages.SwapMsg;
import common.gameState.ModifiableGameState;
import common.model.Player;
import common.model.Tile;

/**
 * The callback used when a new player notifies the will to swap two tiles.
 */
final class SwapCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param state the game data.
     */
    SwapCallback(final GameClient client, final ModifiableGameState state) {
        super(client, state);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SwapMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        final SwapMsg message = (SwapMsg) rawMessage;
        final Tile startTile = message.getStartTile();
        final Tile destTile = message.getDestTile();
        final Player selector = message.getSelector();
        getGameState().swapTiles(startTile, destTile, selector);
    }
}
