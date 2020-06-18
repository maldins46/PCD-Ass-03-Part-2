package client.messages;


public abstract class GenericMsg implements Message {
    private String sender;

    public GenericMsg(final String sender) {
        this.sender = sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }
}
