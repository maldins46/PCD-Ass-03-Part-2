package client.messages;

public final class RequestAckMsg extends GenericMsg {
    private String ackedPlayer;

    public RequestAckMsg(final String sender, final String ackedPlayer) {
        super(sender);
        this.ackedPlayer = ackedPlayer;
    }

    public String getAckedPlayer() {
        return ackedPlayer;
    }

    public void setAckedPlayer(final String ackedPlayer) {
        this.ackedPlayer = ackedPlayer;
    }
}
