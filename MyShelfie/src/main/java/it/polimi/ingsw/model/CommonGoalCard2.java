package it.polimi.ingsw.model;

public class CommonGoalCard2 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo";
    }

}
