package it.polimi.ingsw.view.OBSMessages;
import it.polimi.ingsw.utility.Position;
/**
 * This message contains the move that the user wants to perform
 */
public class OBS_MoveMessage extends OBS_Message {
    private  Position[] move;
    private  int column;

    /**
     * Contructor: creates the message.
     *
     * @param move : position of chosen object cards;
     * @param column : chosen library column
     * @author Francesco Lo Mastro
     */
    public OBS_MoveMessage(Position[] move, int column) {
        super(OBS_MessageType.MOVE);
        this.move=move;
        this.column=column;
    }

    /**
     * This method return the position of object cards chosen by the player.
     *
     * @author Francesco Lo Mastro
     */
    public Position[] getMove() {
        return move;
    }

    /**
     * This method return the library column chosen by the player.
     *
     * @author Francesco Lo Mastro
     */
    public int getColumn(){
        return column;
    }
}
