package it.polimi.ingsw.model;
public class CommonGoalCard8 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Due colonne formate ciascuna da 6 diversi tipi di tessere";
    }
}
