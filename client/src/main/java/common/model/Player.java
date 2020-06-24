package common.model;

import java.util.Objects;

/**
 * Class for players in the game.
 */
public final class Player {

    /**
     * The player's name.
     */
    private final String name;

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

    /**
     * Create an empty player.
     * @return The new empty player.
     */
    public static Player empty() {
        return new Player("");
    }

    /**
     * Factory for the player.
     * @param name The player name.
     * @return The player.
     */
    public static Player of(final String name) {
        return new Player(name);
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
}
