package it.polimi.ingsw.model.Enums;

import java.io.Serializable;

public enum Color implements Serializable {
    BLUE(0,"B"),
    GREEN(1,"G"),
    LIGHTBLUE(2,"L"),
    PINK(3,"P"),
    YELLOW(4,"Y"),
    WHITE(5,"W"),
    EMPTY(6, "E");
    private final int relativeInt;
    private static final long serialVersionUID = 1L;
    private final String string;
    private Color(int interoRelativo, String string) {
        this.relativeInt = interoRelativo;
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    public int getRelativeInt() {
        return relativeInt;
    }

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
