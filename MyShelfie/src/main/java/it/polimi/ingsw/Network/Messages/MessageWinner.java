package it.polimi.ingsw.Network.Messages;

public class MessageWinner extends Message{

    String winner;
    int myPoints;

    public MessageWinner(){
        super(MessageType.WINNER);
    }

    public MessageWinner(String first, Integer second) {
        super(MessageType.WINNER);
        this.winner=first;
        this.myPoints = second;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(int myPoints) {
        this.myPoints = myPoints;
    }
}
