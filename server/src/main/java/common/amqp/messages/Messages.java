package common.amqp.messages;

import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

import java.util.Set;

/**
 * Factory used to create messages.
 */
public final class Messages {


    private Messages() { }


    /**
     * Create an AckMsg.
     * @return A fresh AckMsg.
     */
    public static AckMsg createAckMsg() {
        return new AckMsg();
    }


    /**
     * Create a GameStateMsg.
     * @param puzzle The puzzle instance.
     * @param players The player set.
     * @return A fresh GameStateMsg.
     */
    public static GameStateMsg createGameStateMsg(final Puzzle puzzle, final Set<Player> players) {
        return new GameStateMsg(puzzle, players);
    }


    /**
     * Create a NewPlayerMsg.
     * @return A fresh NewPlayerMsg.
     */
    public static NewPlayerMsg createNewPlayerMsg() {
        return new NewPlayerMsg();
    }


    /**
     * Create a RematchMsg.
     * @return A fresh RematchMsg.
     */
    public static RematchMsg createRematchMsg() {
        return new RematchMsg();
    }


    /**
     * Create a SelectMsg.
     * @param selectedTile The tile selected by the player.
     * @return A fresh SelectMsg.
     */
    public static SelectMsg createSelectMsg(final Tile selectedTile) {
        return new SelectMsg(selectedTile);
    }


    /**
     * Create a SwapMsg.
     * @param startTile The first tile selected by the player.
     * @param destTile The second tile selected by the player.
     * @return A fresh SwapMSg.
     */
    public static SwapMsg createSwapMsg(final Tile startTile, final Tile destTile) {
        return new SwapMsg(startTile, destTile);
    }
}
