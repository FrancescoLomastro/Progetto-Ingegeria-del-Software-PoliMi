package it.polimi.ingsw.model.Cards;

import java.io.Serializable;

/**
 * This class is the abstract "Card" class that all other types of card extend.
 *
 * @author Riccardo Figini
 * */
public abstract class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    private String description;

    /**constructor
     * @author Riccardo figini
     * @param description description of the card to initialize
     * */
    public Card(String description){
        this.description=description;
    }
    /**constructor
     * @author Riccardo Figini*/
    public Card(){}

    /**Return card's description
     * @author Riccardo Figini
     * @return description*/
    public String getDescription() {
        return description;
    }
    /**set card's description
     *  @author Riccardo Figini
     *  @param description : card's description*/
    public void setDescription(String description) {
        this.description = description;
    }
}
