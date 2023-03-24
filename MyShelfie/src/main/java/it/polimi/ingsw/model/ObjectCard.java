package it.polimi.ingsw.model;

public class ObjectCard extends Card{
    private final Color color;
    private final Type type;

    public ObjectCard(String description, Color color, Type type) {
        super(description);
        this.color=color;
        this.type=type;
    }
    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

}