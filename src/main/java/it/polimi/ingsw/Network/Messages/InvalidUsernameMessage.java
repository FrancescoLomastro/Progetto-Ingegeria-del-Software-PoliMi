package it.polimi.ingsw.Network.Messages;

public class InvalidUsernameMessage extends Message {
    public InvalidUsernameMessage() {
        super("Server",MessageType.INVALID_USERNAME_MESSAGE);
    }
}
