package common.client.config;

import common.client.messages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class for every type of messagge.
 */
public final class MessageTypes {


    /**
     * A map with all names of messagges and his implementation.
     */
    private static final Map<String, Class<? extends Message>> MSG_TYPES = initializeTypes();


    private MessageTypes() { }

    /**
     * Initialize all possible messagge.
     * @return A map that have name an
     */
    private static Map<String, Class<? extends Message>> initializeTypes() {
        Map<String, Class<? extends Message>> msgTypes = new HashMap<>();
        msgTypes.put("new-player-msg", NewPlayerMsg.class);
        msgTypes.put("game-state-msg", GameStateMsg.class);
        msgTypes.put("ack-msg", AckMsg.class);
        msgTypes.put("select-msg", SelectMsg.class);
        msgTypes.put("swap-msg", SwapMsg.class);
        msgTypes.put("rematch-msg", RematchMsg.class);
        return msgTypes;
    }


    /**
     * It's return an implementation of messagge from a given name.
     * @param type The name of messagge.
     * @return The type of messagge.
     */
    public static Class<? extends Message> getClassFromType(final String type) {
       return MSG_TYPES.get(type);
    }


    /**
     * It's return the name of a message type.
     * @param message The messagge class.
     * @return The name of messagge type.
     */
    public static String getTypeFromMessage(final Message message) {
        for (Entry<String, Class<? extends Message>> entry : MSG_TYPES.entrySet()) {
            if (message.getClass().isInstance(entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }
}
