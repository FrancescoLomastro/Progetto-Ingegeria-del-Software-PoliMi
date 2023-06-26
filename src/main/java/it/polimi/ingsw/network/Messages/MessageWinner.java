package it.polimi.ingsw.network.Messages;

import it.polimi.ingsw.utility.Couple;

import java.util.ArrayList;

/**
 * This is a message used by server to all players about the winner of the game, providing also a sorted
 * ranking in decreasing order
 */
public class MessageWinner extends Message{
    private final String winnerGame;
    private final int myPoints;
    private ArrayList<Couple<String, Integer>> finalRanking;
    public MessageWinner(String first, Integer second, ArrayList<Couple<String, Integer>> finalRanking) {
        super(MessageType.WINNER_MESSAGE);
        this.winnerGame =first;
        this.myPoints = second;
        this.finalRanking = finalRanking;
    }
    public String getWinnerGame() {
        return winnerGame;
    }

    public int getMyPoints() {
        return myPoints;
    }

    public ArrayList<Couple<String, Integer>> getFinalRanking() {
        return finalRanking;
    }

    public void setFinalRanking(ArrayList<Couple<String, Integer>> finalRanking) {
        this.finalRanking = finalRanking;
    }
}
