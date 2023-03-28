package it.polimi.ingsw.model;
public class CommonGoalCard6 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Cinque tessere dello stesso tipo che formano una diagonale";
    }
}
