package it.polimi.ingsw.network.Messages;
import java.io.Serial;
import java.io.Serializable;

/**
 * This is a generic game message, used to inform clients for game start or game over
 */
public class GameMessage extends Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor: creates a standard game message
     * @param messageType the enum that specifies the type of the message
     */
    public GameMessage(MessageType messageType) {
        super(messageType);
    }
}
