package it.polimi.ingsw.model;

public class CommonGoalCard2 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        String color = library[0][0].getColor();
        if (color.equals(library[0][4].getColor())
                && color.equals(library[5][4].getColor())
                && color.equals(library[5][0].getColor())) {
            return true;
        } else {
            return false;
        }
    }
}
