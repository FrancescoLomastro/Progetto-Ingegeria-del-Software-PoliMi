package it.polimi.ingsw.enums;

import java.io.Serializable;

/**
 * Enum representation of the color of an object card
 */
public enum Color implements Serializable {
    BLUE(0,"\033[34mB\033[0m"),
    GREEN(1,"\033[32mG\033[0m"),
    LIGHTBLUE(2,"\033[36mL\033[0m"),
    PINK(3,"\033[35mP\033[0m"),
    YELLOW(4,"\033[93mY\033[0m"),
    WHITE(5,"W"),
    EMPTY(6, " ");
    private final int relativeInt;
    private static final long serialVersionUID = 1L;
    private final String string;
    private Color(int interoRelativo, String string) {
        this.relativeInt = interoRelativo;
        this.string = string;
    }

    /**
     * @return a colored string representation for the color
     */
    @Override
    public String toString() {
        return string;
    }

    /**
     * @return the relative int of a color
     */
    public int getRelativeInt() {
        return relativeInt;
    }

    /**
     * @param relativeInt an integer corresponding to a certain color
     * @return the enum that owns the relativeInt parameter
     */
    public static Color getEnumFromRelativeInt(int relativeInt)
    {
        for (Color color : Color.values()) {
            if (color.getRelativeInt() == relativeInt) {
                return color;
            }
        }
        return null;
    }
}
