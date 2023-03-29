package it.polimi.ingsw.model;
public enum Type {
    FIRST(0),
    SECOND(1),
    THIRD(2);
    private final int relativeInt;
    public static int numOfValues = 3;
    private Type(int relativeInt) {
        this.relativeInt = relativeInt;
    }

    public int getRelativeInt() {
        return relativeInt;
    }

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
