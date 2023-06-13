package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.enums.Type;

import java.io.Serializable;

public class ObjectCard extends Card implements Serializable {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectCard)) {
            return false;
        }
        ObjectCard other = (ObjectCard) obj;
        return this.color == other.color && this.type == other.type;
    }

    @Override
    public String toString() {
            return ""+color;
    }
}