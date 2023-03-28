package it.polimi.ingsw.model;
public enum Color {
    BLUE(0),
    GREEN(1),
    LIGHTBLUE(2),
    PINK(3),
    YELLOW(4),
    BEIGE(5);   
    private final int relativeInt;
    public static int numOfValues = 6;
    private Color(int interoRelativo) {
        this.relativeInt = interoRelativo;
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
