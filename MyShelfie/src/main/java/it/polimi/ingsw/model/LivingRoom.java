package it.polimi.ingsw.model;

import main.java.it.polimi.ingsw.model.*;

public class LivingRoom {
    private Grid grid;
    private ScoreCard scoreCard;
    private CommonGoalCard[] commonGoalCards;
    private int numCommonGoalCards;

    public LivingRoom(int numPlayers, int livingRoomScore, int numCommonGoalCards)
    {
        this.grid = new Grid(numPlayers);
        this.scoreCard= new ScoreCard(livingRoomScore);
        this.numCommonGoalCards=numCommonGoalCards;
        this.commonGoalCards = new CommonGoalCard[numCommonGoalCards];
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    private void generateCommonGoalCards()
    {
        //not implemented yet
    }

    public Grid getGrid() {
        return grid;
    }

    public ScoreCard getScoreCard() {
        return scoreCard;
    }

    public CommonGoalCard[] getCommonGoalCards() {
        return commonGoalCards;
    }

    public int getNumCommonGoalCards() {
        return numCommonGoalCards;
    }
}
