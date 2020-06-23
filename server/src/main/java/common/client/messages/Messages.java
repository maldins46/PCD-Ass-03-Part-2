package common.client.messages;


import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

import java.util.Set;

/**
 * Factory for create messages.
 */
public interface Messages {

    static AckMsg createAckMsg(final String sender, final Player ackedPlayer){
        return new AckMsg(sender, ackedPlayer);
    }

    static GameStateMsg createGameStateMsg(final String sender, final Puzzle puzzle, final Set<Player> players, final boolean win){
        return new GameStateMsg(sender, puzzle, players, win);
    }

    static NewPlayerMsg createNewPlayerMsg(final String sender, final Player player){
        return new NewPlayerMsg(sender, player);
    }

    static RematchMsg createRematchMsg(final String sender){
        return new RematchMsg(sender);
    }

    static SelectMsg createSelectMsg(final String sender, final Tile selectedTile, final Player selector){
        return new SelectMsg(sender, selectedTile, selector);
    }

    static SwapMsg createSwapMsg(final String sender, final Tile startTile, final Tile destTile, final Player selector){
        return new SwapMsg(sender, startTile,destTile, selector);
    }
}