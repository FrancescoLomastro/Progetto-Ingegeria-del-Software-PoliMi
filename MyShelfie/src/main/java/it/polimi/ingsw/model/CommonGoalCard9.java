package it.polimi.ingsw.model;
public class CommonGoalCard9 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Due righe formate ciascuna da 5 diversi tipi di tessere.";
    }
}
