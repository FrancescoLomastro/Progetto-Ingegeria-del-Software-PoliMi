package it.polimi.ingsw.model.LivingRoom;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.CardGenerator.*;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;


/**
 * This class represents the living room of the game, a class that contains
 * the central grid, the card generator and the extracted commonGoalCard.
 */
public class LivingRoom implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Grid grid;
    private int centralScorePoints=1;
    private final CommonGoalCard[] commonGoalCards;
    private final int numCommonGoalCards;
    private final CardGenerator cardGenerator;




    /**
     * Constructor: Build the central grid and generates the common goal cards.
     * @author Francesco Lo Mastro
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
     * @author Francesco Lo Mastro
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
     * @author Francesco Lo Mastro
     * @return amount of points in the central score card
     */
    public int consumeCentralPoints() {
        int points= centralScorePoints;
        centralScorePoints=0;
        return points;
    }




    /**It returns the grid of the game (not the copy, the original)
     * @return The grid object in the living room
     * @author Francesco Lo Mastro
     */
    public Grid getGrid() {
        return grid;
    }




    /**It returns central point score
     * @author Francesco Lo Mastro
     * @return the amount of point available at the center of the living room
     */
    public int getCentralScorePoints() {
        return centralScorePoints;
    }




    /**It returns a vector of common goal card
     * @author Francesco Lo Mastro
     * @return the array of common goal cards in the library
     */
    public CommonGoalCard[] getCommonGoalCards() {
        return commonGoalCards;
    }

}
