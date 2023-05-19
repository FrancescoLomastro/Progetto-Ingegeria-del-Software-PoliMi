package it.polimi.ingsw.Network.Messages;

public class AcceptedLoginMessage extends Message {
    private int numberOfPlayers;

    public AcceptedLoginMessage(int numberOfPlayers) {
        super("Server", MessageType.ACCEPTED_LOGIN_MESSAGE);

        this.numberOfPlayers=numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
