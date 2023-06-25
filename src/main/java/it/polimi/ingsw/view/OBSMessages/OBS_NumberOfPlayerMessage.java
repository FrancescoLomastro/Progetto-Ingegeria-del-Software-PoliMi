package it.polimi.ingsw.view.OBSMessages;
/**This message is used to send player's number of a new game*/
public class OBS_NumberOfPlayerMessage extends OBS_Message {
    private final int numberOfPlayers;
    public OBS_NumberOfPlayerMessage(int value) {
        super(OBS_MessageType.NUMBER_OF_PLAYERS);
        this.numberOfPlayers=value;
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
