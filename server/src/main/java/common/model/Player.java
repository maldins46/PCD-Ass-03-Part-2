package common.model;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
