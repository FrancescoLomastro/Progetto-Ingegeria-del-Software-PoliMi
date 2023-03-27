package it.polimi.ingsw.model;

public abstract class Card{
    private String description;
    private int height;
    private int width;

    public Card(String description, int height, int width){
        this.description=description;
        this.height=height;
        this.width=width;
    }

    public Card(String description){
        this.description=description;
    }

    public Card(){
    }

    public int getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }

    public int getWidth() {
        return width;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
