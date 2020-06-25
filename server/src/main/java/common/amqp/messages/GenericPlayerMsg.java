package common.amqp.messages;


import common.model.Player;

/**
 * Implements a generic message originated from a player. It is
 * characterized by a sender field that contains the player that
 * originated it.
 */
public abstract class GenericPlayerMsg implements PlayerMsg {

    /**
     * The player that sent the message.
     */
    private Player sender;


    /**
     * Getter for sender player field.
     * @return The sender player.
     */
    @Override
    public final Player getSenderPlayer() {
        return sender;
    }


    /**
     * Setter for the sender player field.
     * @param sender The sender player.
     */
    @Override
    public void setSenderPlayer(final Player sender) {
        this.sender = sender;
    }
}
