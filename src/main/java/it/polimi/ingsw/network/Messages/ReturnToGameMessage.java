package it.polimi.ingsw.network.Messages;

/**
 * This message notifies clients that they will be return to an old interrupted game
 */
public class ReturnToGameMessage extends Message {
    /**
     * Constructor: Builds a message with a type RETURN_TO_OLD_GAME_MESSAGE
     * @author: Francesco Gregorio Lo Mastro
     */
    public ReturnToGameMessage(){
        super(MessageType.RETURN_TO_OLD_GAME_MESSAGE);
    }
}
