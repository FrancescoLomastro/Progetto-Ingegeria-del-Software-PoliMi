package it.polimi.ingsw.network.Messages;

/**
 * class for messages about the not availability of a move
 * @author Andrea Ferrini
 * */
public class MessageAfterMoveNegative extends Message{
    private final String invalidmessage;
    /**
     * constructor
     * */
    public MessageAfterMoveNegative(String message){
        super(MessageType.AFTER_MOVE_NEGATIVE);
        invalidmessage=message;
    }
    public String getInvalidMessage() {
        return invalidmessage;
    }
}
