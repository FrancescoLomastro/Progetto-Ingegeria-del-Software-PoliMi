package it.polimi.ingsw.Network.Messages;

/**
 * class for messages about the not availability of a move
 * @author Andrea Ferrini
 * */
public class MessageAfterMoveNegative extends Message{

    /**
     * constructor
     * */
    public MessageAfterMoveNegative(){
        super(MessageType.AFTER_MOVE_NEGATIVE);
    }
}
