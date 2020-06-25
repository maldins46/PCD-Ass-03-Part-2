package common.amqp.messages;

import common.model.Player;

/**
 * Characterizes a message sent by a player. All of these message have a field
 * that indicates the sender player, that could be set after the message
 * construction with a setter.
 */
public interface PlayerMsg extends Message {

    /**
     * Returns the player that sent the message.
     * @return The player that sent the message.
     */
    Player getSenderPlayer();


    /**
     * Setter for the sender player field.
     * @param sender The player that sent the message.
     */
    void setSenderPlayer(Player sender);
}
