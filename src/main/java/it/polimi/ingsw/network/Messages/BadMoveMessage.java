package it.polimi.ingsw.network.Messages;

/**
 * This message is used when a player sends a not valid move
 */
public class BadMoveMessage extends Message{
    private final String moveError;

    /**
     * Constructor: Creates a message with the reason of the negative move
     * @param moveErrorString a string with the reason of the negative move
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public BadMoveMessage(String moveErrorString){
        super(MessageType.BAD_MOVE_ANSWER);
        this.moveError =moveErrorString;
    }

    /**
     * @return a string with the reason of the negative move
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public String getMoveError() {
        return moveError;
    }
}
