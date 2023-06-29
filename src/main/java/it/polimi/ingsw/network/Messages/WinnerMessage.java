package it.polimi.ingsw.network.Messages;

import it.polimi.ingsw.utility.Couple;

import java.util.ArrayList;

/**
 * This is a message used by server to all players about the winner of the game, providing also a sorted
 * ranking in decreasing order
 */
public class WinnerMessage extends Message{
    private final int myPoints;
    private ArrayList<Couple<String, Integer>> finalRanking;

    /**
     * Constructor: Creates a message with final game ranking
     * @param myPoints the total points that the receiver of the message has realized
     * @param finalRanking an arraylist with the game ranking
     * @author: Francesco Gregorio Lo Mastro
     */
    public WinnerMessage(Integer myPoints, ArrayList<Couple<String, Integer>> finalRanking) {
        super(MessageType.WINNER_MESSAGE);
        this.myPoints = myPoints;
        this.finalRanking = finalRanking;
    }

    /**It returns the points that player gains during the game
     * @return the total points that the receiver of the message has realized
     * @author: Francesco Gregorio Lo Mastro
     */
    public int getMyPoints() {
        return myPoints;
    }

    /**It returnees the final ranking. It is used to show the classification
     * @return an arraylist with the game ranking
     * @author: Francesco Gregorio Lo Mastro
     */
    public ArrayList<Couple<String, Integer>> getFinalRanking() {
        return finalRanking;
    }

}
