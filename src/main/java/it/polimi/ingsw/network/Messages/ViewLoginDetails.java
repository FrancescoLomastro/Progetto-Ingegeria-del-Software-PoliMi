package it.polimi.ingsw.network.Messages;

public class ViewLoginDetails extends Message {
    private final int chosenTechnology;
    private final String chosenUsername;
    private final String chosenAddress;
    private final int chosenPort;
    public ViewLoginDetails(String senderUsername, String chosenUsername, int chosenTechnology, String chosenAddress, int chosenPort) {
        super(senderUsername,MessageType.VIEW_LOGIN_DETAILS);
        this.chosenTechnology=chosenTechnology;
        this.chosenUsername=chosenUsername;
        this.chosenPort=chosenPort;
        this.chosenAddress=chosenAddress;
    }

    public int getChosenTechnology() {
        return chosenTechnology;
    }

    public String getChosenUsername() {
        return chosenUsername;
    }

    public String getChosenAddress() {
        return chosenAddress;
    }

    public int getChosenPort() {
        return chosenPort;
    }
}
