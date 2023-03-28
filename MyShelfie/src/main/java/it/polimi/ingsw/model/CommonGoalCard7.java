package it.polimi.ingsw.model;
public class CommonGoalCard7 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Quattro righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere";
    }
}
