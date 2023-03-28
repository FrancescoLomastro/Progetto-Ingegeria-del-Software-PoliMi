package it.polimi.ingsw.model;
public abstract class Card{
    private String description;
    private int height;
    private int width;

    /**constructor
     * @uthor Riccardo figini
     * @param description: description of the card to initialize
     * */
    public Card(String description){
        this.description=description;
    }
    /**constructor
     * @author Riccardo Figini*/
    public Card(){}

    /**Return card's height
     * @author Riccardo Figini
     * @return height*/
    public int getHeight() {
        return height;
    }
    /**Return card's description
     * @author Riccardo Figini
     * @return description*/
    public String getDescription() {
        return description;
    }
    /**Return card's width
     * @author Riccardo Figini
     * @return  width*/
    public int getWidth() {
        return width;
    }
    /**set card's description
     *  @author Riccardo Figini
     *  @param description : card's description*/
    public void setDescription(String description) {
        this.description = description;
    }
    /**set card's height
     *  @author Riccardo Figini
     *  @param height: height*/
    public void setHeight(int height) {
        this.height = height;
    }
    /**set card's width
     *  @author Riccardo Figini
     *  @param width: width */
    public void setWidth(int width) {
        this.width = width;
    }
}
