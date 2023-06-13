package it.polimi.ingsw.network.Messages;

public class PlayerNumberAnswer extends Message{

    private int playerNumber;

    public PlayerNumberAnswer(String userName, int playerNumber) {
        super(userName, MessageType.PLAYER_NUMBER_ANSWER);
        this.playerNumber=playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
