package it.polimi.ingsw.Network.Messages;

import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;

public class MessageWinner extends Message{
    private final String winner;
    private final int myPoints;

    private ArrayList<Couple<String, Integer>> finalRanking;
    public MessageWinner(String first, Integer second, ArrayList<Couple<String, Integer>> finalRanking) {
        super(MessageType.WINNER);
        this.winner=first;
        this.myPoints = second;
        this.finalRanking = finalRanking;
    }
    public String getWinner() {
        return winner;
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
