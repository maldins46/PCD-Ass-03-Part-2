package common.client.messages;


import common.client.config.Destinations;
import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

import java.util.Set;

/**
 * Factory for create messages.
 */
public final class Messages {

    private Messages() { }

    public static AckMsg createAckMsg(final Player ackedPlayer){
        return new AckMsg(Destinations.PERSONAL_QUEUE, ackedPlayer);
    }

    public static GameStateMsg createGameStateMsg(final Puzzle puzzle, final Set<Player> players, final boolean win){
        return new GameStateMsg(Destinations.PERSONAL_QUEUE, puzzle, players, win);
    }

    public static NewPlayerMsg createNewPlayerMsg(final Player player){
        return new NewPlayerMsg(Destinations.PERSONAL_QUEUE, player);
    }

    public static RematchMsg createRematchMsg(){
        return new RematchMsg(Destinations.PERSONAL_QUEUE);
    }

    public static SelectMsg createSelectMsg(final Tile selectedTile, final Player selector){
        return new SelectMsg(Destinations.PERSONAL_QUEUE, selectedTile, selector);
    }

    public static SwapMsg createSwapMsg(final Tile startTile, final Tile destTile, final Player selector){
        return new SwapMsg(Destinations.PERSONAL_QUEUE, startTile,destTile, selector);
    }
}
