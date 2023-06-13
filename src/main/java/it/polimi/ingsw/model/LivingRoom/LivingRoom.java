package it.polimi.ingsw.model.LivingRoom;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.CardGenerator.*;

import java.io.IOException;
import java.io.Serializable;


/**
 * This class represents the living room of the game, a class that contains
 * the central grid, the card generator and the extracted commonGoalCard.
 */
public class LivingRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private Grid grid;
    private int centralScorePoints=1;
    private CommonGoalCard[] commonGoalCards;
    private int numCommonGoalCards;
    private CardGenerator cardGenerator;




    /**
     * Constructor: Build the central grid and generates the common goal cards.
     * @param numPlayers The number of players that will use the living room
     * @param numCommonGoalCards The number of common goal cards
     * @param cardGenerator is the card to be use to generate living room cards
     * @throws IOException
     */
    public LivingRoom(int numPlayers, int numCommonGoalCards, CardGenerator cardGenerator) throws IOException {
        this.cardGenerator= cardGenerator;
        this.grid = new Grid(numPlayers,this.cardGenerator);
        this.numCommonGoalCards = numCommonGoalCards;
        this.commonGoalCards = new CommonGoalCard[numCommonGoalCards];
        generateCommonGoalCards();
    }




    /**
     * This method is used to generate the common goal card of the living room
     */
    private void generateCommonGoalCards()
    {
        for(int i=0; i<numCommonGoalCards; i++)
        {
            commonGoalCards[i] = cardGenerator.generateCommonGoalCard();
        }
    }


    /**
     * Its used when the first library of the game is filled with object cards.
     * This method consumes the central score card and returns the amount of points.
     * @return amount of points in the central score card
     */
    public int consumeCentralPoints() {
        int points= centralScorePoints;
        centralScorePoints=0;
        return points;
    }


    public Grid getGrid() {
        return grid;
    }
    public int getCentralScorePoints() {
        return centralScorePoints;
    }
    public CommonGoalCard[] getCommonGoalCards() {
        return commonGoalCards;
    }

}
