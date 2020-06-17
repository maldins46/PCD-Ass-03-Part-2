package communication.messages;

import communication.MessageTypes;

public class NewPlayerMsg implements Message {
    public static final MessageTypes MESSAGE_TYPE = MessageTypes.NEW_PLAYER_MSG;

    private String playerName;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
