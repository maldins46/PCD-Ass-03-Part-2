package communication;

import communication.messages.Message;
import communication.messages.NewPlayerMsg;

public enum MessageTypes {
    NEW_PLAYER_MSG {
        @Override
        public String toString() {
            return "new-player-msg";
        }
    };


    public static MessageTypes valueOfString(final String text) {
        for (MessageTypes type : MessageTypes.values()) {
            if (type.toString().equals(text)) {
                return type;
            }
        }

       return null;
    }

    public static MessageTypes valueFromMessage(final Message message) {
        if (message instanceof NewPlayerMsg) {
            return NEW_PLAYER_MSG;
        } else {
            return null;
        }
        // todo switch
    }
}
