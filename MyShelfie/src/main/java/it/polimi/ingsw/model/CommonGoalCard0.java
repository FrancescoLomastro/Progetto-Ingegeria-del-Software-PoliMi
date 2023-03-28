package it.polimi.ingsw.model;

public class CommonGoalCard0 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        return false;
    }

    @Override
    public String getDescription() {
        return "Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.";
    }

}