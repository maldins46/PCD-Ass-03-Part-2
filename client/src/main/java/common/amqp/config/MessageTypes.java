package common.amqp.config;

import common.amqp.messages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utility class used to handle the equivalence between the message type,
 * defined as a string, and the class type of the POJO. This conversion is
 * used as the message type is includer inside header of the messages.
 */
public final class MessageTypes {

    /**
     * A map associating every class name with the class instance.
     */
    private static final Map<String, Class<? extends Message>> MSG_TYPES = initializeTypes();


    private MessageTypes() { }


    /**
     * Initializes all available messages.
     * @return A map associating every class name with the class instance.
     */
    private static Map<String, Class<? extends Message>> initializeTypes() {
        final Map<String, Class<? extends Message>> msgTypes = new HashMap<>();
        msgTypes.put("new-player-msg", NewPlayerMsg.class);
        msgTypes.put("game-state-msg", GameStateMsg.class);
        msgTypes.put("ack-msg", AckMsg.class);
        msgTypes.put("select-msg", SelectMsg.class);
        msgTypes.put("swap-msg", SwapMsg.class);
        msgTypes.put("rematch-msg", RematchMsg.class);
        return msgTypes;
    }


    /**
     * Returns the message Class that corresponds to the given message type String.
     * @param type The message type, as a String.
     * @return The message type, as a Class instance.
     */
    public static Class<? extends Message> getClassFromType(final String type) {
       return MSG_TYPES.get(type);
    }


    /**
     * Returns the message type String that corresponds to the given message.
     * @param message The message type, as a Class instance.
     * @return The message type, as a String.
     */
    public static String getTypeFromMessage(final Message message) {
        for (Entry<String, Class<? extends Message>> entry : MSG_TYPES.entrySet()) {
            if (message.getClass().equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }
}
