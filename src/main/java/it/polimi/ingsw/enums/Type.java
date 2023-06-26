package it.polimi.ingsw.enums;

import java.io.Serializable;

/**
 * Enum representation of the color of an object card, each card can have 3 different skin called types
 */
public enum Type implements Serializable {
    FIRST(0),
    SECOND(1),
    THIRD(2);
    private static final long serialVersionUID = 1L;
    private final int relativeInt;
    public static int numOfValues = 3;
    private Type(int relativeInt) {
        this.relativeInt = relativeInt;
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
    public static Type getEnumFromRelativeInt(int relativeInt)
    {
        for (Type type : Type.values()) {
            if (type.getRelativeInt() == relativeInt) {
                return type;
            }
        }
        return null;
    }
}
