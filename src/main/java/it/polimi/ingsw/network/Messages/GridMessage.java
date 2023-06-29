package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.model.Cards.ObjectCard;

/**
 * This message is used by server to update players on a central grid update.
 */
public class GridMessage extends Message {
    private final TypeOfGridMessage typeOfGridMessage;
    private final ObjectCard[][] grid;

    /**
     * Constructor: creates a grid update message
     * @param objectCards the matrix representation of the new central grid
     * @param typeOfGridMessage an enum that specifies if the update is related to a player move (used for GUI refill)
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public GridMessage(ObjectCard[][] objectCards, TypeOfGridMessage typeOfGridMessage){
        super(MessageType.UPDATE_GRID_MESSAGE);
        this.grid=objectCards;
        this.typeOfGridMessage=typeOfGridMessage;
    }

    /**It returns the updated grid
     * @return a matrix of object cards representing the actual central grid
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public ObjectCard[][] getGrid() {
        return grid;
    }

    /**
     * Subclass used to recognize when a grid update is related to a player move or its an initial update (used for GUI refill)
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public enum TypeOfGridMessage{
        UPDATE_AFTER_MOVE, INIT, NEED_TO_BE_PRINTED;
    }

    /**It returns the type of the message
     * @return an enum representing the type of grid update
     */
    public TypeOfGridMessage getTypeOfGridMessage() {
        return typeOfGridMessage;
    }
}
