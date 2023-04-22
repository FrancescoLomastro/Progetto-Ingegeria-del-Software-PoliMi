package it.polimi.ingsw.Network.Messages;

/**
 * class for messages about the not availability of a move
 * @author Andrea Ferrini
 * */
public class MessageAfterMoveNegative extends Message{
    private final String invelidmessage;
    /**
     * constructor
     * */
    public MessageAfterMoveNegative(String message){
        super(MessageType.AFTER_MOVE_NEGATIVE);
        invelidmessage=message;
    }
    public String getInvalidMessage() {
        return invelidmessage;
    }
}
