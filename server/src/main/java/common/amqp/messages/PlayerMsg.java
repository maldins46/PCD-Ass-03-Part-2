package common.amqp.messages;

import common.model.Player;

public interface PlayerMsg extends Message {
    Player getSenderPlayer();

    void setSenderPlayer(Player sender);
}
