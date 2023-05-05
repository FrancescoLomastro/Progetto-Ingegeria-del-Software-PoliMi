package it.polimi.ingsw.View.OBSMessages;


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
