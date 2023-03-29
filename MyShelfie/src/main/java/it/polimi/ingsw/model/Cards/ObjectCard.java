package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Enums.*;

public class ObjectCard extends Card {
    private final Color color;
    private final Type type;

    /**Constructor
     * @author Riccardo Figini
     * @param description objectcard's descrption
     * @param color objectcard's color
     * @param type objectcard's type
     * */
    public ObjectCard(String description, Color color, Type type) {
        super(description);
        this.color = color;
        this.type = type;
    }
    /**Get color
     * @author Riccardo Figini
     * @return color*/
    public Color getColor() {
        return color;
    }
    /**Get Type
     * @author Riccardo Figini
     * @return type*/
    public Type getType() {
        return type;
    }

}