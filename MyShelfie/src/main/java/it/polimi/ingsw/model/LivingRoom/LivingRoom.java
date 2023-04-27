package it.polimi.ingsw.model.LivingRoom;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.CardGenerator.*;

import java.io.IOException;
import java.io.Serializable;

public class LivingRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private Grid grid;
    private ScorePointCard scoreCard;
    private CommonGoalCard[] commonGoalCards;
    private int numCommonGoalCards;
    private CardGenerator cardGenerator;

    public LivingRoom(int numPlayers, int numCommonGoalCards, CardGenerator cardGenerator) throws IOException {
        this.cardGenerator= cardGenerator;
        this.grid = new Grid(numPlayers,this.cardGenerator);
        this.scoreCard= new ScorePointCard(numPlayers);
        this.numCommonGoalCards=numCommonGoalCards;
        this.commonGoalCards = new CommonGoalCard[numCommonGoalCards];
        generateCommonGoalCards();
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    private void generateCommonGoalCards()
    {
        commonGoalCards[0] = cardGenerator.generateCommonGoalCard();
        commonGoalCards[1] = cardGenerator.generateCommonGoalCard();
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
