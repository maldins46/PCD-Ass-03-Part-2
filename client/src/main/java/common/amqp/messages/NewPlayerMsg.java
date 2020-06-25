package common.amqp.messages;


import common.model.Player;

/**
 * Class that represent a message for inform that a new player want to join the
 * game.
 */
public final class NewPlayerMsg extends GenericPlayerMsg {

    /**
     * The new player that want join the game.
     */
    private final Player player;

    NewPlayerMsg(final Player player) {
        this.player = player;
    }

    /**
     * Getter for the player.
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }
}
