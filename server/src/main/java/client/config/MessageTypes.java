package client.config;

import client.messages.Message;
import client.messages.NewPlayerMsg;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class MessageTypes {
    private static final Map<String, Class<? extends Message>> MSG_TYPES = initializeTypes();

    private static Map<String, Class<? extends Message>> initializeTypes() {
        Map<String, Class<? extends Message>> msgTypes = new HashMap<>();
        msgTypes.put("new-player-msg", NewPlayerMsg.class);
        // todo put types
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
