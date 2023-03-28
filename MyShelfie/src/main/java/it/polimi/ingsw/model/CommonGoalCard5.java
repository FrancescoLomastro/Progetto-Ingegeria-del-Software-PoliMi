package it.polimi.ingsw.model;
public class CommonGoalCard5 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Otto tessere dello stesso tipo. Non ci sono restrizioni sulla posizione di queste tessere";
    }
}
