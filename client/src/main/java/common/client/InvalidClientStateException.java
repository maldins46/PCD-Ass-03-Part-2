package common.client;

public final class InvalidClientStateException extends Exception {

    public InvalidClientStateException() {
        super("Used a GameClient method in an invalid state.");
    }
}
