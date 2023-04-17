package it.polimi.ingsw.Network.Messages;

public class AcceptedLoginMessage extends Message {
    public AcceptedLoginMessage() {
        super("Server", MessageType.ACCEPTEDLOGIN_MESSAGE);
    }
}
