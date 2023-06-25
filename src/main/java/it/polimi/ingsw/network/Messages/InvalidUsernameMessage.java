package it.polimi.ingsw.network.Messages;

/**
 * This message is used to send an inavild username answer to the client
 */
public class InvalidUsernameMessage extends Message {
    /**
     * Constructor: Creates an invalid username message
     */
    public InvalidUsernameMessage() {
        super("Server",MessageType.INVALID_USERNAME_ANSWER);
    }
}
