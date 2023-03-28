package it.polimi.ingsw.model;

public class CommonGoalCard4 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti. Colonne diverse possono avere combinazioni diverse di tipi di tessere";
    }
}