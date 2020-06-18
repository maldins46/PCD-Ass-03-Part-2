package communication;

import communication.messages.Message;

/**
 * todo
 */
public interface MicroCallback {
    /**
     * todo
     */
    String getMessageType();

    /**
     * todo
     */
    String getDestination();

    /**
     * todo
     */
    void execute(Message message);
}
