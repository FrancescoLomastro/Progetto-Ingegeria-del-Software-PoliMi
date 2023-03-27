package it.polimi.ingsw.model;
public abstract class CommonGoalCard extends Card{
    private int point;
    public CommonGoalCard(String descrption) {
        super(descrption);
        point = 8;
    }
    public CommonGoalCard() {
    }
    public abstract boolean isSatisfied(Library lib);

    public void changePoint(){
        switch (point) {
            case 8 -> point = 6;
            case 6 -> point = 4;
            case 4 -> point = 2;
            default -> {
            }
        }
    }

}