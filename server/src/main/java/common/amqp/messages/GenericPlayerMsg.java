package common.amqp.messages;


import common.model.Player;

/**
 * Generic class for a Msg.
 */
public abstract class GenericPlayerMsg implements PlayerMsg {
    /**
     * The message's sender.
     */
    private Player sender;

    @Override
    public final Player getSenderPlayer() {
        return sender;
    }

    @Override
    public void setSenderPlayer(final Player sender) {
        this.sender = sender;
    }
}
