package it.polimi.ingsw.Network.Messages;

public class PlayerNumberAnswer extends Message{

    private int playerNumber;

    public PlayerNumberAnswer(String userName, int playerNumber) {
        super(userName, MessageType.PLAYERNUMBER_ANSWER);
        this.playerNumber=playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
