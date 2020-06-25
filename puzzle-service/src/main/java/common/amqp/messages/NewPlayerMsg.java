package common.amqp.messages;


import common.model.Player;

/**
 * Message used to inform the puzzle service that a new player wants to
 * join the game.
 */
public final class NewPlayerMsg extends GenericPlayerMsg {

    /**
     * The player that wants to be added to the game corresponds to the sender
     * field.
     * @return The player that wants to be added to the game.
     */
    public Player getPlayer() {
        return getSenderPlayer();
    }
}
