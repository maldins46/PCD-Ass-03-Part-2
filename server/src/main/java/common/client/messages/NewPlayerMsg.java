package common.client.messages;


import common.model.Player;

/**
 * Class that represent a message for inform that a new player want to join the
 * game.
 */
public final class NewPlayerMsg extends GenericMsg {

    /**
     * The new player that want join the game.
     */
    private final Player player;

    NewPlayerMsg(final String sender, final Player player) {
        super(sender);
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
