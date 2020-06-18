package client.config;

import client.messages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class MessageTypes {
    private static final Map<String, Class<? extends Message>> MSG_TYPES = initializeTypes();

    private static Map<String, Class<? extends Message>> initializeTypes() {
        Map<String, Class<? extends Message>> msgTypes = new HashMap<>();
        msgTypes.put("new-player-msg", NewPlayerMsg.class);
        msgTypes.put("game-data-msg", GameDataMsg.class);
        msgTypes.put("ack-msg", AckMsg.class);
        msgTypes.put("select-msg", SelectMsg.class);
        msgTypes.put("swap-msg", SwapMsg.class);
        msgTypes.put("rematch-msg", RematchMsg.class);
        return msgTypes;
    }


    /**
     * todo
     */
    public static Class<? extends Message> getClassFromType(final String type) {
       return MSG_TYPES.get(type);
    }


    /**
     * todo
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
