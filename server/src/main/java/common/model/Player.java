package common.model;

import java.util.Objects;

/**
 * Class that models the concept of player.
 */
public final class Player {

    /**
     * The player's name.
     */
    private final String name;


    /**
     * A player can be build using a player name, as it is identified
     * only by that.
     * @param name The player's name.
     */
    private Player(final String name) {
        this.name = name;
    }


    /**
     * Getter for the name.
     * @return The name.
     */
    public String getName() {
        return name;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    /**
     * Creates an empty player.
     * @return The new empty player.
     */
    public static Player empty() {
        return new Player("");
    }


    /**
     * Creates generic player with a passed name.
     * @param name The player name.
     * @return The player.
     */
    public static Player of(final String name) {
        return new Player(name);
    }
}
