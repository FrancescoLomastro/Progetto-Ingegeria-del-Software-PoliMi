package it.polimi.ingsw.model.LivingRoom;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.CardGenerator.*;

import java.io.IOException;

public class LivingRoom {
    private Grid grid;
    private ScorePointCard scoreCard;
    private CommonGoalCard[] commonGoalCards;
    private int numCommonGoalCards;
    private CardGenerator cardGenerator;

    public LivingRoom(int numPlayers, int livingRoomScore, int numCommonGoalCards, CardGenerator cardGenerator) throws IOException {
        this.cardGenerator= cardGenerator;
        this.grid = new Grid(numPlayers,this.cardGenerator);
        this.scoreCard= new ScorePointCard(livingRoomScore);
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

    public ScorePointCard getScoreCard() {
        return scoreCard;
    }

    public CommonGoalCard[] getCommonGoalCards() {
        return commonGoalCards;
    }

    public int getNumCommonGoalCards() {
        return numCommonGoalCards;
    }
}
