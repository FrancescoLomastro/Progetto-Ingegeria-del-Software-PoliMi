package it.polimi.ingsw.Network.Messages;

public class MessageWinner extends Message{
    private final String winner;
    private final int myPoints;

    public MessageWinner(String first, Integer second) {
        super(MessageType.WINNER);
        this.winner=first;
        this.myPoints = second;
    }
    public String getWinner() {
        return winner;
    }

    public int getMyPoints() {
        return myPoints;
    }
}
