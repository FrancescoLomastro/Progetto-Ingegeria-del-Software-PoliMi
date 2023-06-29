package it.polimi.ingsw.view.OBSMessages;

/**
 * This message contains the number of players that user wants to play with
 */
public class OBS_NumberOfPlayerMessage extends OBS_Message {
    private final int numberOfPlayers;

    /**
     * Constructor: Creates the message
     *
     * @param value : the chosen number of player;
     * @author Francesco Lo Mastro
     */
    public OBS_NumberOfPlayerMessage(int value) {
        super(OBS_MessageType.NUMBER_OF_PLAYERS);
        this.numberOfPlayers=value;
    }

    /**
     * This method return the number of players chosen by the player.
     *
     * @author Francesco Lo Mastro
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
