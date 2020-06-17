package communication;

import communication.messages.Message;

public interface Callback {
    MessageTypes getMessageType();
    void execute(Message message);
}
