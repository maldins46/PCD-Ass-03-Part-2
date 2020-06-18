package client.messages;

public final class RequestAckMsg implements Message {
    private String player;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(final String player) {
        this.player = player;
    }
}
