package it.polimi.ingsw.model;

public class CommonGoalCard1 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Quattro tessere dello stesso tipo ai quattro angoli della Libreria";
    }
}
