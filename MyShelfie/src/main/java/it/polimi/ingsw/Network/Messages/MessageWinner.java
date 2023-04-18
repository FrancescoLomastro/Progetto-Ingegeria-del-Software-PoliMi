package it.polimi.ingsw.Network.Messages;

public class MessageWinner extends Message{

    String winner;
    int winnerPoints;

    public MessageWinner(){
        super(MessageType.WINNER);
    }

    public MessageWinner(String first, Integer second) {
        super(MessageType.WINNER);
        this.winner=first;
        this.winnerPoints = second;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getWinnerPoints() {
        return winnerPoints;
    }

    public void setWinnerPoints(int winnerPoints) {
        this.winnerPoints = winnerPoints;
    }
}
