package it.polimi.ingsw.network.Messages;

/**
 * This message is used from clients to tell the server the max number of players that will join the lobby
 */
public class PlayerNumberAnswer extends Message{

    private int playerNumber;

    /**
     * Constructor: Creates a message the max number of players that will join the lobby
     * @param userName the username of the sender
     * @param playerNumber the max number of player
     * @author Francesco Lo Mastro
     */
    public PlayerNumberAnswer(String userName, int playerNumber) {
        super(userName, MessageType.PLAYER_NUMBER_ANSWER);
        this.playerNumber=playerNumber;
    }

    /**It returns the number of plaeyr
     * @return the max number of player in the message
     * @author Francesco Lo Mastro
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
}
