package it.polimi.ingsw.model;

public class CommonGoalCard3 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2. Le tessere dei due gruppi devono essere dello stesso tipo";
    }
}
