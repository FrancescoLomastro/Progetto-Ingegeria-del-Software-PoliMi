package it.polimi.ingsw.Network.Messages;

public class InvalidUsernameMessage extends Message {
    public InvalidUsernameMessage() {
        super("Server",MessageType.INVALIDUSERNAME_MESSAGE);
    }
}
