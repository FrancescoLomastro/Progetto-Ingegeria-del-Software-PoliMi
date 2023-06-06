package it.polimi.ingsw.View.OBSMessages;


public class OBS_InitialInfoMessage extends OBS_Message {
    private final String chosenUsername;
    private final int chosenTechnology;
    private final String chosenAddress;
    private final int chosenPort;

    public String getChosenUsername() {
        return chosenUsername;
    }

    public int getChosenTechnology() {
        return chosenTechnology;
    }

    public String getChosenAddress() {
        return chosenAddress;
    }

    public int getChosenPort() {
        return chosenPort;
    }

    public OBS_InitialInfoMessage(String chosenUsername, int chosenTechnology, String chosenAddress, int chosenPort) {
        super(OBS_MessageType.INITIAL_INFO);
        this.chosenUsername=chosenUsername;
        this.chosenTechnology=chosenTechnology;
        this.chosenAddress=chosenAddress;
        this.chosenPort=chosenPort;
    }
}
