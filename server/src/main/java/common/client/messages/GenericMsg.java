package common.client.messages;


/**
 * Generic class for a Msg.
 */
public abstract class GenericMsg implements Message {
    /**
     * The message's sender.
     */
    private final String sender;

    /**
     * Constructor for a generic message.
     * @param sender The message' sender.
     */
    public GenericMsg(final String sender) {
        this.sender = sender;
    }


    @Override
    public final String getSender() {
        return sender;
    }
}
