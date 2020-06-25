package common.amqp.messages;


import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

import java.util.Set;

/**
 * Factory for create messages.
 */
public final class Messages {

    private Messages() { }

    /**
     * Create an AckMsg.
     * @param ackedPlayer The receiver of the message.
     * @return The AckMsg.
     */
    public static AckMsg createAckMsg(final Player ackedPlayer) {
        return new AckMsg(ackedPlayer);
    }

    /**
     * Create a GameStateMsg.
     * @param puzzle The puzzle updated.
     * @param players The participants players.
     * @return The GameStateMsg.
     */
    public static GameStateMsg createGameStateMsg(final Puzzle puzzle,
                                                  final Set<Player> players) {
        return new GameStateMsg(puzzle, players);
    }

    /**
     * Create a NewPlayerMsg.
     * @param player The participant player.
     * @return The NewPlayerMsg.
     */
    public static NewPlayerMsg createNewPlayerMsg(final Player player) {
        return new NewPlayerMsg(player);
    }

    /**
     * Create a RematchMsg.
     * @return The RematchMsg.
     */
    public static RematchMsg createRematchMsg() {
        return new RematchMsg();
    }

    /**
     * Create a SelectMsg.
     * @param selectedTile The first tile selected from a player.
     * @param selector The player selector.
     * @return The SelectMsg.
     */
    public static SelectMsg createSelectMsg(final Tile selectedTile,
                                            final Player selector) {
        return new SelectMsg(selectedTile,
                selector);
    }

    /**
     * Create a SwapMsg.
     * @param startTile The first tile selected from a player.
     * @param destTile The second tile selected from a player.
     * @param selector Theplayer selector.
     * @return The SwapMSg.
     */
    public static SwapMsg createSwapMsg(final Tile startTile,
                                        final Tile destTile,
                                        final Player selector) {
        return new SwapMsg(startTile, destTile,
                selector);
    }
}
