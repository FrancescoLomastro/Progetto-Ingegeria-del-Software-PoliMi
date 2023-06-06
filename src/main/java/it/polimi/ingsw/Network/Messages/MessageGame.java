package it.polimi.ingsw.Network.Messages;
import java.io.Serial;
import java.io.Serializable;
public class MessageGame extends Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public MessageGame(MessageType messageType) {
        super(messageType);
    }
}
