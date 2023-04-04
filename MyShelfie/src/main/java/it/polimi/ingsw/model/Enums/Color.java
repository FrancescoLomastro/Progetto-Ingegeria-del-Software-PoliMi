package it.polimi.ingsw.model.Enums;
public enum Color {
    BLUE(0,"B"),
    GREEN(1,"G"),
    LIGHTBLUE(2,"L"),
    PINK(3,"P"),
    YELLOW(4,"Y"),
    WHITE(5,"W");
    private final int relativeInt;
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
