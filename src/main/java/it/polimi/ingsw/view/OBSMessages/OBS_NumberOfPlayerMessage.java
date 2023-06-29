package it.polimi.ingsw.view.OBSMessages;
/**This message is used to send player's number of a new game*/
public class OBS_NumberOfPlayerMessage extends OBS_Message {
    private final int numberOfPlayers;

    /**
     * This method is used to construct a number of player message to be sent to the server.
     *
     * @param value : the chosen number of player;
     * @author Francesco Gregorio Lo Mastro
     */
    public OBS_NumberOfPlayerMessage(int value) {
        super(OBS_MessageType.NUMBER_OF_PLAYERS);
        this.numberOfPlayers=value;
    }

    /**
     * This method return the number of players chosen by the player.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
