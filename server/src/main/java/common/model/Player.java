package common.model;

public final class Player {
    private String name;

    public Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Player generateEmpty() {
        return new Player("");
    }

    public static Player of(final String name) {
        return new Player(name);
    }
}
